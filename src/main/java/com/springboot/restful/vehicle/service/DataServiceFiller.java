package com.springboot.restful.vehicle.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.springboot.restful.vehicle.model.PerMinPricing;
import com.springboot.restful.vehicle.model.PricingPlan;
import com.springboot.restful.vehicle.model.RequestModel;
import com.springboot.restful.vehicle.model.ResponseModel;
import com.springboot.restful.vehicle.model.StationStatus;
import com.springboot.restful.vehicle.model.VehicleDocksAvailable;
import com.springboot.restful.vehicle.model.VehicleTypeAvailable;

@EnableScheduling
@Service
public class DataServiceFiller {

  final static Logger logger = Logger.getLogger(DataServiceFiller.class);

  @Autowired
  private DataService dataservice;


  public DataService getDataservice() {
    return dataservice;
  }

  private String getResultString(String urlStr) {

    logger.info("getResultString   url: " + urlStr);
    String inline = "";

    try {
      URL url = new URL(urlStr);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();

      // Getting the response code
      int responsecode = conn.getResponseCode();

      if (responsecode != 200) {

        logger.error("Responsecode is different from 200!  responsecode: " + responsecode);
        throw new RuntimeException("HttpResponseCode: " + responsecode);

      } else {

        Scanner scanner = new Scanner(url.openStream());

        // Write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
          inline += scanner.nextLine();
        }

        // Close the scanner
        scanner.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return inline;
  }


  @Scheduled(fixedDelay = 1800000) // works every 30 minutes
  public void fillPricingPlan() {

    Set<PricingPlan> pricingPlans = new HashSet<PricingPlan>();
    try {
      String resultStr = getResultString(
          "https://data-sharing.tier-services.io/tier_paris/gbfs/2.2/system-pricing-plans");

      JSONParser parser = new JSONParser();
      JSONObject obj = (JSONObject) parser.parse(resultStr);

      JSONObject data = (JSONObject) obj.get("data");
      JSONArray plans = (JSONArray) data.get("plans");

      for (Object o : plans) {
        JSONObject plan = (JSONObject) o;

        PricingPlan pricingPlan = new PricingPlan();
        pricingPlan.setCurrency((String) plan.get("currency"));
        pricingPlan.setDescription((String) plan.get("description"));
        pricingPlan.setName((String) plan.get("name"));
        pricingPlan.setPlanId((String) plan.get("plan_id"));
        pricingPlan.setPrice((long) plan.get("price"));
        pricingPlan.setTaxable((boolean) plan.get("is_taxable"));


        JSONArray perMinPricingArr = (JSONArray) plan.get("per_min_pricing");
        for (Object perMinPricingItem : perMinPricingArr) {
          JSONObject perMinPricingObj = (JSONObject) perMinPricingItem;

          PerMinPricing perMinPricing = new PerMinPricing();
          perMinPricing.setInterval((long) perMinPricingObj.get("interval"));
          perMinPricing.setRate((double) perMinPricingObj.get("rate"));
          perMinPricing.setStart((long) perMinPricingObj.get("start"));

          pricingPlan.getPricingList().add(perMinPricing);
        }

        pricingPlans.add(pricingPlan);
      }

      logger.info("fillPricingPlan   pricingPlans.size(): " + pricingPlans.size());
      dataservice.setPricingPlans(pricingPlans);

    } catch (Exception e) {
      logger.error("An exception occured when filling pricing plan set" + e.toString());
      e.printStackTrace();
    }
  }

  @Scheduled(fixedDelay = 300000) // works every 5 minutes
  public void fillStationStatus() {

    Set<StationStatus> stationStatusSet = new HashSet<StationStatus>();
    try {
      String resultStr = getResultString(
          "https://data-sharing.tier-services.io/tier_paris/gbfs/2.2/station-status");

      // Using the JSON simple library parse the string into a json object
      JSONParser parser = new JSONParser();
      JSONObject obj = (JSONObject) parser.parse(resultStr);

      JSONObject data = (JSONObject) obj.get("data");
      JSONArray stations = (JSONArray) data.get("stations");


      for (Object o : stations) {
        JSONObject station = (JSONObject) o;

        StationStatus stationStatus = new StationStatus();

        stationStatus.setInstalled((boolean) station.get("is_installed"));
        stationStatus.setRenting((boolean) station.get("is_renting"));
        stationStatus.setReturning((boolean) station.get("is_returning"));
        stationStatus.setLastReported((Long) station.get("last_reported"));
        stationStatus.setNumBikesAvailable((Long) station.get("num_bikes_available"));
        stationStatus.setNumDocksAvailable((Long) station.get("num_docks_available"));
        stationStatus.setStationId((String) station.get("station_id"));

        JSONArray vehicleTypesAvailableArr = (JSONArray) station.get("vehicle_types_available");
        if (vehicleTypesAvailableArr != null) {
          for (Object objVehicleType : vehicleTypesAvailableArr) {
            JSONObject vehicleType = (JSONObject) objVehicleType;

            VehicleTypeAvailable vehicleTypeAvailable = new VehicleTypeAvailable();
            vehicleTypeAvailable.setCount((Long) vehicleType.get("count"));
            vehicleTypeAvailable.setVehicleTypeId((String) vehicleType.get("vehicle_type_id"));

            stationStatus.getVehicleTypesAvailableList().add(vehicleTypeAvailable);
          }
        }

        JSONArray vehicleDocksAvailableArr = (JSONArray) station.get("vehicle_docks_available");
        if (vehicleDocksAvailableArr != null) {
          for (Object objVehicleDock : vehicleDocksAvailableArr) {
            JSONObject vehicleDock = (JSONObject) objVehicleDock;

            VehicleDocksAvailable vehicleDocksAvailable = new VehicleDocksAvailable();
            vehicleDocksAvailable.setCount((Long) vehicleDock.get("count"));

            if (vehicleDocksAvailable.getCount() > 0) {
              JSONArray vehicleTypeIdArr = (JSONArray) vehicleDock.get("vehicle_type_ids");
              if (vehicleTypeIdArr != null) {
                for (Object vehicleTypeId : vehicleTypeIdArr) {
                  if (stationStatus.getStationId().equals("PARIS-54-RUE-BOURET-75019-FANTASMO")) {
                    System.out.println("vehicleTypeId: " + vehicleTypeId);
                  }
                  vehicleDocksAvailable.getVehicleTypeIds().add((String) vehicleTypeId);
                }
              }
            }

            stationStatus.getVehicleDocksAvailableList().add(vehicleDocksAvailable);
          }
        }

        stationStatusSet.add(stationStatus);
      } // for

      dataservice.setStationStatuses(stationStatusSet);

      logger.info(
          "dataservice.getStationStatuses().size(): " + dataservice.getStationStatuses().size());

    } catch (Exception e) {
      logger.error("An exception occured when filling station status set" + e.toString());
      e.printStackTrace();
    }

  }


  public ResponseModel getFilteredData(RequestModel requestModel) {

    Set<StationStatus> stationSet = dataservice.getStationStatuses();

    logger.info("stationSet.size(): " + stationSet.size());

    stationSet = stationSet.stream().filter(n -> n.isRenting() == requestModel.isRenting()
        && n.isReturning() == requestModel.isReturning()).collect(Collectors.toSet());

    if (requestModel.isBikeAvailable()) {
      stationSet = stationSet.stream()
          .filter(n -> n.getNumBikesAvailable() != null && n.getNumBikesAvailable() > 0)
          .collect(Collectors.toSet());
    } else {
      stationSet = stationSet.stream()
          .filter(n -> n.getNumBikesAvailable() == null || n.getNumBikesAvailable() == 0)
          .collect(Collectors.toSet());
    }

    if (requestModel.isDockAvailable()) {
      stationSet = stationSet.stream()
          .filter(n -> n.getNumDocksAvailable() != null && n.getNumDocksAvailable() > 0)
          .collect(Collectors.toSet());
    } else {
      stationSet = stationSet.stream()
          .filter(n -> n.getNumDocksAvailable() == null || n.getNumDocksAvailable() == 0)
          .collect(Collectors.toSet());
    }

    if (requestModel.getStationId() != null && !requestModel.getStationId().equals("")) {
      stationSet =
          stationSet.stream().filter(n -> n.getStationId().equals(requestModel.getStationId()))
              .collect(Collectors.toSet());
    }

    logger.info("stationSet.size() after filtering: " + stationSet.size());

    ResponseModel responseModel = new ResponseModel();
    responseModel.setStationStatusSet(stationSet);

    Set<PricingPlan> pricingPlans = dataservice.getPricingPlans();
    logger.info("pricingPlans.size(): " + pricingPlans.size());

    if (requestModel.getPrice() != null && requestModel.getPrice() > 0) {
      pricingPlans = pricingPlans.stream().filter(n -> n.getPrice() <= requestModel.getPrice())
          .collect(Collectors.toSet());
    }

    logger.info("pricingPlans.size() after filtering: " + pricingPlans.size());
    responseModel.setPricingPlanSet(pricingPlans);

    return responseModel;
  }

}
