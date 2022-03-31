package com.springboot.restful.vehicle.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import com.springboot.restful.vehicle.model.PricingPlan;
import com.springboot.restful.vehicle.model.StationStatus;

@Component
public class DataService {

  private Set<StationStatus> stationStatuses = new HashSet<StationStatus>();
  private Set<PricingPlan> pricingPlans = new HashSet<PricingPlan>();

  public DataService() {
    super();
  }

  public Set<StationStatus> getStationStatuses() {
    return stationStatuses;
  }

  public Set<PricingPlan> getPricingPlans() {
    return pricingPlans;
  }

  public void setStationStatuses(Set<StationStatus> stationStatuses) {
    this.stationStatuses = stationStatuses;
  }

  public void setPricingPlans(Set<PricingPlan> pricingPlans) {
    this.pricingPlans = pricingPlans;
  }



}
