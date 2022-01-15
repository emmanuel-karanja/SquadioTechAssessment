package com.main.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Statement {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statementId")
	private long statementId;
	private String accountNumber;
	private String description;
	private Double amount;
	
	private long userId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accountId", nullable = false)
	private Account account;
	private LocalDate transactionDate;
	
	public Statement() {
		//this.userId=this.account.getUser().getId();
	}
	
	
	
	public Statement(String accNum,String desc, Double amount,LocalDate date) {
		
		accountNumber=accNum;
		description=desc;
		this.amount=amount;
		this.transactionDate=date;
		//this.userId=account.getUser().getId();
		
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long id) {
		this.userId=userId;
	}
	
	
	public long getId() {
		return statementId;
	}
	
	public void setAccount(Account acc) {
		account=acc;
		this.userId=acc.getUser().getId();
	}
	
	public Account getAccount() {
		return account;
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
	
	@Column(name="descriptions")
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
