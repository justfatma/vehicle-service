package com.springboot.restful.vehicle.model;

import java.util.Set;

public class ResponseModel {

  private Set<StationStatus> stationStatusSet;
  private Set<PricingPlan> pricingPlanSet;

  public Set<StationStatus> getStationStatusSet() {
    return stationStatusSet;
  }

  public void setStationStatusSet(Set<StationStatus> stationStatusSet) {
    this.stationStatusSet = stationStatusSet;
  }

  public Set<PricingPlan> getPricingPlanSet() {
    return pricingPlanSet;
  }

  public void setPricingPlanSet(Set<PricingPlan> pricingPlanSet) {
    this.pricingPlanSet = pricingPlanSet;
  }

}
