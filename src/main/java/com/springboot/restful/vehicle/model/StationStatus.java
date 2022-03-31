package com.springboot.restful.vehicle.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StationStatus {

  private String stationId;
  private boolean installed;
  private boolean renting;
  private boolean returning;
  private Long lastReported;
  private Long numBikesAvailable;
  private List<VehicleTypeAvailable> vehicleTypesAvailableList =
      new ArrayList<VehicleTypeAvailable>();
  private Long numDocksAvailable;
  private List<VehicleDocksAvailable> vehicleDocksAvailableList =
      new ArrayList<VehicleDocksAvailable>();


  public String getStationId() {
    return stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  public boolean isInstalled() {
    return installed;
  }

  public void setInstalled(boolean installed) {
    this.installed = installed;
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

  public Long getLastReported() {
    return lastReported;
  }

  public void setLastReported(Long lastReported) {
    this.lastReported = lastReported;
  }

  public Long getNumBikesAvailable() {
    return numBikesAvailable;
  }

  public void setNumBikesAvailable(Long numBikesAvailable) {
    this.numBikesAvailable = numBikesAvailable;
  }

  public Long getNumDocksAvailable() {
    return numDocksAvailable;
  }

  public void setNumDocksAvailable(Long numDocksAvailable) {
    this.numDocksAvailable = numDocksAvailable;
  }

  public List<VehicleTypeAvailable> getVehicleTypesAvailableList() {
    return vehicleTypesAvailableList;
  }

  public void setVehicleTypesAvailableList(List<VehicleTypeAvailable> vehicleTypesAvailableList) {
    this.vehicleTypesAvailableList = vehicleTypesAvailableList;
  }

  public List<VehicleDocksAvailable> getVehicleDocksAvailableList() {
    return vehicleDocksAvailableList;
  }

  public void setVehicleDocksAvailableList(List<VehicleDocksAvailable> vehicleDocksAvailableList) {
    this.vehicleDocksAvailableList = vehicleDocksAvailableList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(installed, lastReported, numBikesAvailable, numDocksAvailable, renting,
        returning, stationId, vehicleDocksAvailableList, vehicleTypesAvailableList);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    StationStatus other = (StationStatus) obj;
    return installed == other.installed && Objects.equals(lastReported, other.lastReported)
        && Objects.equals(numBikesAvailable, other.numBikesAvailable)
        && Objects.equals(numDocksAvailable, other.numDocksAvailable) && renting == other.renting
        && returning == other.returning && Objects.equals(stationId, other.stationId)
        && Objects.equals(vehicleDocksAvailableList, other.vehicleDocksAvailableList)
        && Objects.equals(vehicleTypesAvailableList, other.vehicleTypesAvailableList);
  }

}
