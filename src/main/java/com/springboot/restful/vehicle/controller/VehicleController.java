package com.springboot.restful.vehicle.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.springboot.restful.vehicle.model.PricingPlan;
import com.springboot.restful.vehicle.model.RequestModel;
import com.springboot.restful.vehicle.model.ResponseModel;
import com.springboot.restful.vehicle.model.StationStatus;
import com.springboot.restful.vehicle.service.DataServiceFiller;

@RestController
public class VehicleController {

  @Autowired
  DataServiceFiller dataServiceFiller;

  // MAIN METHOD
  @GetMapping("/vehicles")
  public ResponseEntity<ResponseModel> getVehicles(@RequestBody RequestModel requestModel) {

    return new ResponseEntity<ResponseModel>(dataServiceFiller.getFilteredData(requestModel),
        HttpStatus.OK);
  }

  // This method is used to confirm successful retrieval of data from the relevant source.
  @GetMapping("/stationStatuses")
  public Set<StationStatus> getStationStatuses() {
    Set<StationStatus> stationStatusList = dataServiceFiller.getDataservice().getStationStatuses();
    if (stationStatusList != null) {
      return stationStatusList;
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  // This method is used to confirm successful retrieval of data from the relevant source.
  @GetMapping("/pricingplan")
  public Set<PricingPlan> getPricingPlans() {
    Set<PricingPlan> pricingPlanList = dataServiceFiller.getDataservice().getPricingPlans();
    if (pricingPlanList != null) {
      return pricingPlanList;
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

}
