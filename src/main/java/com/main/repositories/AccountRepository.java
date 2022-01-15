package com.main.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.main.entities.Account;
import com.main.entities.User;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	//@Query("select a from ACCOUNTS a where a.user_id =:userId")
	public List<Account> findAllByUser(@Param("userId")User user);
	List<Account>  findAll();
	public Optional<Account> getById(long id);
}
