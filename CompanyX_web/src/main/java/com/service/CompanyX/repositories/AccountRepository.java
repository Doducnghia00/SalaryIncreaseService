package com.service.CompanyX.repositories;

import com.service.CompanyX.formSubmission.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByEmailAndPassword(String email, String password);



}
