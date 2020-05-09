package com.capg.pecunia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.pecunia.entities.Transaction;
@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer>{
	

}
