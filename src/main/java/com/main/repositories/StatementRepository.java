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
	public Optional<List<Statement>> getAllByTransactionDateBetween(LocalDate startDate,LocalDate endDate);
	
	public Optional<List<Statement>> getAllByAmountBetween(Double lowerBound,Double upperBound);
	
	public Optional<Statement>  getById(long id);
	public List<Statement> findAll();
	
	//@Query("select a from STATEMENTS a where a.account_number =:accountNumber")
	public List<Statement> getByAccountNumber(@Param("accountNumber") String acccountNumber);
}
