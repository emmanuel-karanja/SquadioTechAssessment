package com.main.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.main.dtos.AccountResponse;
import com.main.dtos.StatementResponse;
import com.main.entities.Account;

public interface IAccountService {

	public StatementResponse getById(long userId,long id);
	//public List<AccountResponse> getAll(long userId);
	public List<StatementResponse> getStatementsByDate(long userId,LocalDate startDate,LocalDate endDate);
	public List<StatementResponse> getStatementsByAmount(long userId,Double lower,Double upper);
	public String generateRandomStatements(int count);
	public List<StatementResponse> getStatements(long userId);
	public String clearStatements();
	public List<StatementResponse> getByAccountNumber(long userId,String accountNumber);
	public List<AccountResponse> getAccountsByUserId(long userId);
	public List<AccountResponse> getAll(long userId);
}
