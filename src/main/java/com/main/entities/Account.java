package com.main.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name="accounts",uniqueConstraints={@UniqueConstraint(columnNames={"accountNumber"}),})
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountId")
	private long accountId;
	private String accountType;
	

	private String accountNumber;//it can contain letters am sure//
	private String IBAN;
	private Double balance;
	private String currency;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private Set<Statement> statements;
	
	public Account() {
		
	}
	
    public Account(String accountType,String accountNumber, String IBan,String currency) {
    	this.accountNumber=accountNumber;
    	this.IBAN=IBan;
    	this.accountType=accountType;
    	this.currency=currency;
    	this.balance=0.00;
    }
    
    public long getId() {
    	return accountId;
    }
    
    public void setId(long id) {
    	this.accountId=id;
    }
    
    public User getUser() {
    	return user;
    }
    
    public void setUser(User usr) {
    	user=usr;
    }
    public String getCurrency() {
    	return currency;
    }
    public void setCurrency(String currency) {
    	this.currency=currency;
    }
    
    public void setIBAN(String IBan) {
    	this.IBAN=IBan;
    }
    
    
    public void setAccountType(String accountType) {
    	this.accountType=accountType;
    }
    
    public void setBalance(Double balance) {
    	this.balance=balance;
    }
    public void setAccountNumber(String accountNumber) {
    	this.accountNumber=accountNumber;
    }
    
    public Double getBalance() {
    	return this.balance;
    }
    
    public String getAccountNumber() {
    	return this.accountNumber;
    }
    
    public String getAccountType() {
    	return this.accountType;
    }
    
    public String getIBAN() {
    	return this.IBAN;
    }
}
