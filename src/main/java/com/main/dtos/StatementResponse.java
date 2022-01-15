package com.main.dtos;

import java.time.LocalDate;
import java.util.Date;

public class StatementResponse {
	private long id;
	private String accountNumber;
	private String description;
	private Double amount;
	private LocalDate transactionDate;
	private long userId;
	
	public StatementResponse(long id,String accNum,String desc, Double amount,LocalDate date) {
		this.id=id;
		accountNumber=accNum;
		description=desc;
		this.amount=amount;
		this.transactionDate=date;
		
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	public void setUserId(long userId) {
		this.userId=userId;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public long getId() {
		return id;
	}
	
	
	public void setAccountNumber(String accNum) {
		accountNumber=accNum;
		
	}
	
	public void setDescription(String desc) {
		description=desc;
	}
	
	public void setAmount(Double amnt) {
		amount=amnt;
	}
	
	public void setTransactionDate(LocalDate date) {
		transactionDate=date;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public LocalDate getTransationDate() {
		return transactionDate;
	}
}
