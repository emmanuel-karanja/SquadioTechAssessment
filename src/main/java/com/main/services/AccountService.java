package com.main.services;

import java.time.LocalDate;
import com.main.utils.Utils;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dtos.AccountResponse;
import com.main.dtos.ApiResponse;
import com.main.dtos.StatementResponse;
import com.main.entities.Account;
import com.main.entities.Statement;
import com.main.entities.User;
import com.main.exceptions.BadRequestException;
import com.main.exceptions.ResourceNotFoundException;
import com.main.repositories.AccountRepository;
import com.main.repositories.StatementRepository;

@Service
public class AccountService implements IAccountService {

	Logger logger=LoggerFactory.getLogger(AccountService.class);
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private StatementRepository statementRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public StatementResponse getById(long userId,long id) {
		// TODO Auto-generated method stub
	    Statement statement=statementRepository.getByIdAndUserId(id,userId)
	    		 .orElseThrow(()-> new ResourceNotFoundException("Statement","statement",id));
	    
	    return this.convertStatementFromEntity(statement);
	}

	@Override
	public List<StatementResponse> getStatementsByDate(long userId,LocalDate startDate, LocalDate endDate) {
		List<Statement> statements;
		
		User user=userService.getById(userId);
		List<Account> accounts=accountRepository.findAllByUser(user);
		if(startDate ==null && endDate==null) {
			logger.info("no date path");
			
			
			LocalDate endingDate=LocalDate.now();
			LocalDate startingDate=endingDate.minusMonths(3);//set startDate to 3 months in the past
			statements=statementRepository.getAllByTransactionDateBetweenAndUserIdOrderByTransactionDateDesc(startingDate,endingDate, userId)
	        		 .orElseThrow(()-> new ResourceNotFoundException("Statement","statement",startDate));
		}else {
			
			statements=statementRepository.getAllByTransactionDateBetweenAndUserIdOrderByTransactionDateDesc(startDate,endDate,userId)
	        		 .orElseThrow(()-> new ResourceNotFoundException("Statement","statement",startDate));
		}
		 
		//List<Statement> filteredStatements=accounts.stream().filter(a-> a.getAccountNumber())
		
		List<StatementResponse> responses=statements.stream()
		                                                .map(a->convertStatementFromEntity(a))
														.collect(Collectors.toList());
	    return responses;
	}

	@Override
	public List<StatementResponse> getStatementsByAmount(long userId,Double lower, Double upper) {
		// TODO Auto-generated method stub
		try {
		
         List<Statement> statements=statementRepository.getAllByAmountBetweenAndUserId(lower, upper,userId)
        		 .orElseThrow(()-> new ResourceNotFoundException("Statement","statement",upper));
		
		List<StatementResponse> responses=statements.stream()
		                                                .map(a->convertStatementFromEntity(a))
														.collect(Collectors.toList());
	    return responses;
		}catch(Exception e) {
			throw new BadRequestException(new ApiResponse(false,"Could not fetch details "+e.getMessage()));
		}
		
	}

	public AccountResponse convertFromEntity(Account account) {
		AccountResponse response=new AccountResponse();
		response.setId(account.getId());
		response.setAccountNumber(this.maskAccountNumber(account.getAccountNumber(), "#"));
		response.SetAccountType(account.getAccountType());
		response.setCurrency(account.getCurrency());
		response.setBalance(account.getBalance());
		response.setIBAN(account.getIBAN());
		
		return response;
	  
	}
	
	//account number must be masked except for a few digits
	private  String maskAccountNumber(String accountNumber, String mask) {

		final String s = accountNumber.replaceAll("\\D", "");

	    final int start = 3;
	    final int end = s.length() - 3;
	    final String overlay = StringUtils.repeat(mask, end - start);

	    return StringUtils.overlay(s, overlay, start, end);
	   
	}
	
	public String generateRandomStatements(int number) {
		String result="";
	
		try {
			for(int i=0;i<number;i++) {
				int ranAcc=Utils.randomAccNo(1,8);
				double amount=Utils.randomAmount(10.11,1000.99);
				Account account=accountRepository.getById((long) ranAcc)
						.orElseThrow(()->new ResourceNotFoundException("Statement","GettingAccountForStatement",ranAcc));
				
				LocalDate ranDate=Utils.between(LocalDate.of(2020,1,1),LocalDate.now());
				logger.info(ranDate.toString());
				Statement stmt=new Statement(account.getAccountNumber(),"example desc ",amount,ranDate);
				stmt.setAccount(account);
				logger.info("userId: "+account.getUser().getId());
				stmt.setUserId(account.getUser().getId()); //we do it manually for now rather than alter the code and use JdbcTemplate
				statementRepository.save(stmt);
			    result="Random sample Statements generated for accounts";
			    
			}
			
		}catch(Exception e) {
			result=e.getMessage();
			throw new BadRequestException(e.getMessage());
		}
		return result;
	}
	
	public StatementResponse convertStatementFromEntity(Statement statement) {
		StatementResponse response=new StatementResponse(statement.getId(),
				                                         statement.getAccountNumber(),
				                                         statement.getDescription(),
				                                         statement.getAmount(),statement.getTransationDate()
				                                         );
		response.setUserId(statement.getUserId());
		logger.info("get userId:"+statement.getUserId());
		
	
		return response;
	}

	@Override
	public List<AccountResponse> getAll(long userId) {
		// TODO Auto-generated method stub
	  List<AccountResponse> response=null;
	  try {
		 User user=userService.getById(userId);
		  List<Account> accounts=accountRepository.findAllByUser(user);
		  response=accounts.stream()
                  .map(a->convertFromEntity(a))
					.collect(Collectors.toList());
	  }catch(Exception e) {
		  throw new ResourceNotFoundException("Accounts","getAll",e.getMessage());
	  }
	  return response;
	}

	@Override
	public List<StatementResponse> getStatements(long userId) {
		 List<StatementResponse> response=null;
		  try {
			 
			  List<Statement> statements=statementRepository.findAllByUserId(userId);
			  response=statements.stream()
	                  .map(a->convertStatementFromEntity(a))
						.collect(Collectors.toList());
		  }catch(Exception e) {
			  throw new ResourceNotFoundException("Statements","getAll",e.getMessage());
		  }
		  return response;
	}

	@Override
	public String clearStatements() {
		String result="";
		int  num=1000;
		List<Statement> statements=null;
		try {
			for(int i=0;i<num;i++) {
				statements=statementRepository.findAll();
				
			}
			for(Statement stmt: statements) {
				statementRepository.delete(stmt);
			}
		}catch(Exception e) {
			
			throw new BadRequestException(new ApiResponse(false,"statement clearance failed"));
		}
		return result;
	}

	@Override
	public List<StatementResponse> getByAccountNumber(long userId,String accountNumber) {
		// TODO Auto-generated method stub
	   List<StatementResponse> response=null;
	   try {
		   List<Statement> stats=statementRepository.getByAccountNumberAndUserId(accountNumber,userId);
		   response=stats.stream()
	                  .map(a->convertStatementFromEntity(a))
						.collect(Collectors.toList());
		   
	   }catch(Exception e) {
		   throw new ResourceNotFoundException("Statement","not created",e.getMessage());
	   }
	   
	   return response;
	}

	@Override
	public List<AccountResponse> getAccountsByUserId(long id) {
		List<AccountResponse> response=null;
		try {
			User user=userService.getById(id);
			logger.info("Found user: "+user.toString());
			List<Account> accounts=accountRepository.findAllByUser(user);
			
			response=accounts.stream()
	                  .map(a->convertFromEntity(a))
						.collect(Collectors.toList());
		}catch(Exception e) {
			throw new ResourceNotFoundException("Accounts","GetAccountsByUserId",e.getMessage());
		}
		
		return response;
	}
}
