package com.main.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.main.dtos.AccountResponse;
import com.main.dtos.StatementResponse;
import com.main.entities.Account;

public interface IAccountService {

	public StatementResponse getById(long id);
	public List<AccountResponse> getAll();
	public List<StatementResponse> getStatementsByDate(LocalDate startDate,LocalDate endDate);
	public List<StatementResponse> getStatementsByAmount(Double lower,Double upper);
	public String generateRandomStatements(int count);
	public List<StatementResponse> getStatements();
	public String clearStatements();
	public List<StatementResponse> getByAccountNumber(String accountNumber);
	public List<AccountResponse> getAccountsByUserId(long id);
}
