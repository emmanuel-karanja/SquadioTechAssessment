package com.main.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.main.entities.Statement;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
	public Optional<List<Statement>> getAllByTransactionDateBetweenAndUserIdOrderByTransactionDateDesc(LocalDate startDate,LocalDate endDate,long userId);
	
	public Optional<List<Statement>> getAllByAmountBetweenAndUserId(Double lowerBound,Double upperBound,long userId);
	
	public Optional<Statement>  getByIdAndUserId(long id,long userId);
	public List<Statement> findAllByUserId(long userId);
	
	//@Query("select a from STATEMENTS a where a.account_number =:accountNumber")
	public List<Statement> getByAccountNumberAndUserId(@Param("accountNumber") String acccountNumber,long userId);
	
}
