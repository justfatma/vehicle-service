package com.springboot.restful.vehicle.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PricingPlan {

  private String planId;
  private String name;
  private String currency;
  private long price;
  private boolean isTaxable;
  private String description;
  private List<PerMinPricing> pricingList = new ArrayList<PerMinPricing>();

  public String getPlanId() {
    return planId;
  }

  public void setPlanId(String planId) {
    this.planId = planId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }



  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public boolean isTaxable() {
    return isTaxable;
  }

  public void setTaxable(boolean isTaxable) {
    this.isTaxable = isTaxable;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<PerMinPricing> getPricingList() {
    return pricingList;
  }

  public void setPricingList(List<PerMinPricing> pricingList) {
    this.pricingList = pricingList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(currency, description, isTaxable, name, planId, price, pricingList);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PricingPlan other = (PricingPlan) obj;
    return Objects.equals(currency, other.currency)
        && Objects.equals(description, other.description) && isTaxable == other.isTaxable
        && Objects.equals(name, other.name) && Objects.equals(planId, other.planId)
        && Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
        && Objects.equals(pricingList, other.pricingList);
  }


}
