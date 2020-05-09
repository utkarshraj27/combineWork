package com.capg.pecunia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.pecunia.entities.Account;

public interface AccountDao extends JpaRepository<Account, String>{

}
