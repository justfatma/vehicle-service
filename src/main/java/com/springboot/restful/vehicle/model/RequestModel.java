package com.springboot.restful.vehicle.model;

public class RequestModel {

  private String stationId;
  private boolean dockAvailable;
  private boolean bikeAvailable;
  private boolean renting;
  private boolean returning;
  private Long price;



  public String getStationId() {
    return stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  public boolean isDockAvailable() {
    return dockAvailable;
  }

  public void setDockAvailable(boolean dockAvailable) {
    this.dockAvailable = dockAvailable;
  }

  public boolean isBikeAvailable() {
    return bikeAvailable;
  }

  public void setBikeAvailable(boolean bikeAvailable) {
    this.bikeAvailable = bikeAvailable;
  }

  public boolean isRenting() {
    return renting;
  }

  public void setRenting(boolean renting) {
    this.renting = renting;
  }

  public boolean isReturning() {
    return returning;
  }

  public void setReturning(boolean returning) {
    this.returning = returning;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

}
