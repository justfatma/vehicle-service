package com.springboot.restful.vehicle.model;

public class VehicleTypeAvailable {

  private String vehicleTypeId;
  private Long count;

  public String getVehicleTypeId() {
    return vehicleTypeId;
  }

  public void setVehicleTypeId(String vehicleTypeId) {
    this.vehicleTypeId = vehicleTypeId;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }


}
