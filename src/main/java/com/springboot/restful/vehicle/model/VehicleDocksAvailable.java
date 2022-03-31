package com.springboot.restful.vehicle.model;

import java.util.ArrayList;
import java.util.List;

public class VehicleDocksAvailable {

  private List<String> vehicleTypeIds = new ArrayList<String>();
  private Long count;



  public List<String> getVehicleTypeIds() {
    return vehicleTypeIds;
  }

  public void setVehicleTypeIds(List<String> vehicleTypeIds) {
    this.vehicleTypeIds = vehicleTypeIds;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

}
