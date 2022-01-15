package com.main.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.dtos.AccountResponse;
import com.main.dtos.StatementResponse;
import com.main.security.CurrentUser;
import com.main.security.UserPrincipal;
import com.main.services.IAccountService;


@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

	Logger logger=LoggerFactory.getLogger(AccountsController.class);

	IAccountService accountService;
	
	public AccountsController(IAccountService accountService) {
		this.accountService=accountService;
	}
	@GetMapping("/{accountId}")
	public ResponseEntity<StatementResponse> getAccountById(
			@CurrentUser UserPrincipal currentUser,
			@PathVariable(value="userId") long userId,
			@PathVariable(value="accountId") long accountId){
		//for now this is it.
		StatementResponse response=accountService.getById(currentUser.getId(),accountId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<AccountResponse>> getAccountByUserId
	(
		@CurrentUser UserPrincipal currentUser){
		logger.info("all accounts");
		List<AccountResponse> response=accountService.getAccountsByUserId(currentUser.getId());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/statements")
	public ResponseEntity<List<StatementResponse>> getAll(
	@CurrentUser UserPrincipal currentUser,
	@RequestParam(name="startDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate,
	@RequestParam(name="endDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate
	){
    	
		//logger.info("From: "+startDate.toString()+" To: "+endDate.toString());
		List<StatementResponse> response=accountService.getStatementsByDate(currentUser.getId(),startDate,endDate);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@GetMapping("/statements/{account}")
	public ResponseEntity<List<StatementResponse>> getByAccountNumber(
			@CurrentUser UserPrincipal currentUser,
			@PathVariable(value="userId") long userId,
			@PathVariable(name="account",required=true) String accountNumber){
    	
		
		List<StatementResponse> response=accountService.getByAccountNumber(currentUser.getId(),accountNumber);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	//purely for testing purposes only
	@GetMapping("/clear")
	public ResponseEntity<String> clearAllStatements(){
    	
		String response=accountService.clearStatements();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/statements/amounts")
	public ResponseEntity<List<StatementResponse>> getAllByAmounts(
			@CurrentUser UserPrincipal currentUser,
	@RequestParam(name="lowerBound",required=false)Double lowerBound,
	@RequestParam(name="upperBound",required=false)Double upperBound
	){
		logger.info("lowerbound: "+String.valueOf(lowerBound)+"upperBound: "+String.valueOf(upperBound));
    	
		List<StatementResponse> response=accountService.getStatementsByAmount(currentUser.getId(),lowerBound,upperBound);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	//pu
//	@GetMapping("/")
//	public ResponseEntity<List<AccountResponse>> getAllOfThem(){
//    	
//		List<AccountResponse> response=accountService.getAll();
//		return new ResponseEntity<>(response,HttpStatus.OK);
//	}
//	
	//this is just a dummy endpoint to generate statements for accounts
	@GetMapping("/genStats")
	public ResponseEntity<String> generateStatements(@RequestParam(name="number",required=true) int number) {
		String response=accountService.generateRandomStatements(number);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}
