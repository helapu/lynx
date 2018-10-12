package com.helapu.lynx.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.helapu.lynx.entity.enums.RentStatusEnum;
import com.helapu.lynx.entity.enums.RentTypeEnum;

@SuppressWarnings("serial")
public class RentDeal extends SuperEntity<RentDeal> {
	private String companyName;
	private String companyMobile;
	private BigDecimal deposit;
	private RentTypeEnum rentType;
	private BigDecimal price;
	private Timestamp rentAt;
	private String comment;
	private Boolean active;
	private RentStatusEnum status;
	
    @TableField(exist = false)
	private BigDecimal withdrawalBalance;
    
    @TableField(exist = false)
    private List<RentDealItem> rentDealItems;
    
    public RentDeal() {
    	
    }
    public BigDecimal getWithdrawalBalance() {
		// 根据deposit和租赁方式计算剩余可退押金
		return this.deposit.subtract(this.price);
	}
	public void setWithdrawalBalance(BigDecimal withdrawalBalance) {
		this.withdrawalBalance = withdrawalBalance;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyMobile() {
		return companyMobile;
	}
	public void setCompanyMobile(String companyMobile) {
		this.companyMobile = companyMobile;
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public RentTypeEnum getRentType() {
		return rentType;
	}
	public void setRentType(RentTypeEnum rentType) {
		this.rentType = rentType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Timestamp getRentAt() {
		return rentAt;
	}
	public void setRentAt(Timestamp rentAt) {
		this.rentAt = rentAt;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public RentStatusEnum getStatus() {
		return status;
	}
	public void setStatus(RentStatusEnum status) {
		this.status = status;
	}
	public List<RentDealItem> getRentDealItems() {
		return rentDealItems;
	}
	public void setRentDealItems(List<RentDealItem> rentDealItems) {
		this.rentDealItems = rentDealItems;
	}
	
	
}
