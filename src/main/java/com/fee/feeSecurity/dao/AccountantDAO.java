package com.fee.feeSecurity.dao;

import com.fee.feeSecurity.dto.AccountantDto;

import java.util.List;

public interface AccountantDAO {

    List<AccountantDto> findAllStudents();
    AccountantDto findById(Long id);
    void save(AccountantDto accountant);
    void deleteById(int id);
    AccountantDto findByEmail(String email);
    void makePayment();
}
