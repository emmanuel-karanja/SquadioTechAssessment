package com.main.dtos;

public class AccountResponse {

	private long id;
	private String accountType;
	private String accountNumber;
	private String IBAN;
	private Double balance;
	private String currency;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	public String getAccountType() {
		return accountType;
	}
	
	public void SetAccountType(String accountType) {
		this.accountType=accountType;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setBalance(Double balance) {
		this.balance=balance;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public void setAccountNumber(String accNum) {
		this.accountNumber=accNum;
	}
	
	public String getIBAN() {
		return IBAN;
	}
	
	public void setIBAN(String iban) {
		this.IBAN=iban;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency=currency;
	}
	
	
}
