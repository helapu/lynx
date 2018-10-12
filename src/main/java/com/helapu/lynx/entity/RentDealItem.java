package com.helapu.lynx.entity;


@SuppressWarnings("serial")
public class RentDealItem extends SuperEntity<RentDealItem> {

	private Long rentDealId;
	private String rentGood;
	private Float amount;
	
	public RentDealItem() {
		
	}
	public Long getRentDealId() {
		return rentDealId;
	}

	public void setRentDealId(Long rentDealId) {
		this.rentDealId = rentDealId;
	}

	public String getRentGood() {
		return rentGood;
	}

	public void setRentGood(String rentGood) {
		this.rentGood = rentGood;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
}
