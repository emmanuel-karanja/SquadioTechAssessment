package com.main.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.NaturalId;




@Entity
@Table(name="users",uniqueConstraints={@UniqueConstraint(columnNames={"userName"})})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long userId;

    
    private String firstName;//not in the tech assessment requirements but I need it to create UserPrincipal

    private String lastName; //not in the tech assessment requirements but I need it to create UserPrincipal

    @Column(name="pword")
    private String password;//not in the tech assessment requirements but how can we authenticate the user

    @NaturalId
    private String userName;

  
    private String role;
    
    private boolean loggedIn;
    
    public boolean isLoggedIn() {
    	return loggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
    	this.loggedIn=loggedIn;
    }
    
    
   
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Account> accounts;
    
    
    public User() {
    	
    }
    public Set<Account> getAccounts(){
    	return accounts;
    }
    
    
    public void setAccounts(Set<Account> accs) {
    	this.accounts=accs;
    }

    public String getFullName() {
        return firstName != null ? firstName.concat(" ").concat(lastName) : "";
    }


     public User(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = username;
		this.password = password;
    }
     
    public String getRole() {
    	return this.role;
    }
    
    public void serRole(String role) {
    	this.role=role;
    }
   
    
    public boolean isAdmin() {
    	return this.role.trim().toLowerCase().equals("admin");
    }
    public String getFirstName() {
    	return firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public String getUserName() {
    	return userName;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public long getId() {
    	return userId;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName=firstName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName=lastName;
    }
    
    public void setPassword(String password) {
    	this.password=password;
    }
    
    public void setUserName(String userName) {
    	this.userName=userName;
    }


    
	public Collection<String> getRoles() {
		// TODO Auto-generated method stub
	  List<String> roles=new ArrayList<String>();
	  roles.add(role);
	  return roles;
	}

   
}