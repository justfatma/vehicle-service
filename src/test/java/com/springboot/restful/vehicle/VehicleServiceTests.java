package com.springboot.restful.vehicle;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.springboot.restful.vehicle.model.PricingPlan;
import com.springboot.restful.vehicle.model.RequestModel;
import com.springboot.restful.vehicle.model.ResponseModel;
import com.springboot.restful.vehicle.model.StationStatus;
import com.springboot.restful.vehicle.service.DataServiceFiller;

@SpringBootTest
public class VehicleServiceTests {

  @Autowired
  DataServiceFiller dataServiceFiller;


  public void setUp() {
    dataServiceFiller.fillPricingPlan();
    dataServiceFiller.fillStationStatus();
  }

  // {"dockAvailable":true,
  // "bikeAvailable":true,
  // "renting":true,
  // "returning":true,
  // "stationId":"PARIS-53-RUE-DES-ARCHIVES-75003-FANTASMO",
  // "price": 1
  // }

  @Test
  public void getStations_DockAvailable_BikeAvailable_RentingAvailable_ReturningAvailable() {

    setUp();
    RequestModel requestModel = new RequestModel();
    requestModel.setDockAvailable(true);
    requestModel.setBikeAvailable(true);
    requestModel.setRenting(true);
    requestModel.setReturning(true);

    ResponseModel responseModel = dataServiceFiller.getFilteredData(requestModel);

    Set<StationStatus> stationSet = responseModel.getStationStatusSet();
    List<StationStatus> stationList = new ArrayList<>(stationSet);

    assertAll(() -> assertEquals(true, stationList.get(0).getNumDocksAvailable() > 0),
        () -> assertEquals(true, stationList.get(0).getNumBikesAvailable() > 0),
        () -> assertEquals(true, stationList.get(0).isRenting()),
        () -> assertEquals(true, stationList.get(0).isReturning()));
  }

  @Test
  public void getStations_DockNotAvailable_BikeAvailable_RentingAvailable_ReturningAvailable() {

    RequestModel requestModel = new RequestModel();
    requestModel.setDockAvailable(false);
    requestModel.setBikeAvailable(true);
    requestModel.setRenting(true);
    requestModel.setReturning(true);

    ResponseModel responseModel = dataServiceFiller.getFilteredData(requestModel);

    Set<StationStatus> stationSet = responseModel.getStationStatusSet();
    List<StationStatus> stationList = new ArrayList<>(stationSet);

    assertAll(
        () -> assertEquals(true,
            stationList.get(0).getNumDocksAvailable() == null
                || stationList.get(0).getNumDocksAvailable() == 0),
        () -> assertEquals(true, stationList.get(0).getNumBikesAvailable() > 0),
        () -> assertEquals(true, stationList.get(0).isRenting()),
        () -> assertEquals(true, stationList.get(0).isReturning()));
  }

  @Test
  public void getStations_DockAvailable_BikeNotAvailable_RentingAvailable_ReturningAvailable() {
    RequestModel requestModel = new RequestModel();
    requestModel.setDockAvailable(true);
    requestModel.setBikeAvailable(false);
    requestModel.setRenting(true);
    requestModel.setReturning(true);

    ResponseModel responseModel = dataServiceFiller.getFilteredData(requestModel);

    Set<StationStatus> stationSet = responseModel.getStationStatusSet();
    List<StationStatus> stationList = new ArrayList<>(stationSet);

    assertAll(() -> assertEquals(true, stationList.get(0).getNumDocksAvailable() > 0),
        () -> assertEquals(true,
            stationList.get(0).getNumBikesAvailable() == null
                || stationList.get(0).getNumBikesAvailable() == 0),
        () -> assertEquals(true, stationList.get(0).isRenting()),
        () -> assertEquals(true, stationList.get(0).isReturning()));
  }

  @Test
  public void getStations_WithFiltersAndStationId() {
    RequestModel requestModel = new RequestModel();
    requestModel.setDockAvailable(true);
    requestModel.setBikeAvailable(true);
    requestModel.setRenting(true);
    requestModel.setReturning(true);
    requestModel.setStationId("PARIS-53-RUE-DES-ARCHIVES-75003-FANTASMO");

    ResponseModel responseModel = dataServiceFiller.getFilteredData(requestModel);

    Set<StationStatus> stationSet = responseModel.getStationStatusSet();
    List<StationStatus> stationList = new ArrayList<>(stationSet);

    if (stationList.size() > 0) {

      assertAll(() -> assertEquals(true, stationList.get(0).getNumDocksAvailable() > 0),
          () -> assertEquals(true, stationList.get(0).getNumBikesAvailable() > 0),
          () -> assertEquals(true, stationList.get(0).isRenting()),
          () -> assertEquals(true, stationList.get(0).isReturning()),
          () -> assertEquals("PARIS-53-RUE-DES-ARCHIVES-75003-FANTASMO",
              stationList.get(0).getStationId()));

    }
  }

  @Test
  public void getStations_WithFilterAndPrice() {

    RequestModel requestModel = new RequestModel();
    requestModel.setDockAvailable(true);
    requestModel.setBikeAvailable(true);
    requestModel.setRenting(true);
    requestModel.setReturning(true);
    requestModel.setPrice(1L);

    ResponseModel responseModel = dataServiceFiller.getFilteredData(requestModel);

    Set<PricingPlan> pricingPlan = responseModel.getPricingPlanSet();
    List<PricingPlan> pricingList = new ArrayList<>(pricingPlan);

    assertEquals(true, pricingList.get(0).getPrice() <= requestModel.getPrice());

  }

}
