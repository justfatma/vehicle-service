package com.springboot.restful.vehicle.model;

public class PerMinPricing {

  private long start;
  private double rate;
  private long interval;



  public long getStart() {
    return start;
  }

  public void setStart(long start) {
    this.start = start;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  public long getInterval() {
    return interval;
  }

  public void setInterval(long interval) {
    this.interval = interval;
  }

}
