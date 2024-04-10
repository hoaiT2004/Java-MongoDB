package com.example.springbootwithmongo.service;

import com.example.springbootwithmongo.entity.Account;
import com.example.springbootwithmongo.model.*;
import com.example.springbootwithmongo.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    public RegisterAccountResponse registerAccount(RegisterAccountRequest request) {
        logger.info("Chạy chương trình {}","registerAccount");
        var acc = accountRepository.findById(request.getId());
        if (acc.isPresent()){
            logger.info("registerAccount:Tài khoản đã tồn tại!");
            return null;
        }
        logger.info(request+"");
        var account = Account.builder()
                .id(request.getId())
                .name(request.getName())
                .age(request.getAge())
                .date(request.getDate())
                .build();
        var accountResponse = RegisterAccountResponse.builder()
                .id(request.getId())
                .name(request.getName())
                .age(request.getAge())
                .date(request.getDate())
                .build();
        accountRepository.save(account);
        logger.info("Đăng ký thành công");
        return accountResponse;
    }

    public FindAccountResponse findById(String id) {
        logger.info("Chạy chương trình {} với id = {}","findById",id);
        var account = accountRepository.findById(id).orElse(null);
        if (account == null){
            logger.info("registerAccount:Find by Id={}:Tài khoản không tồn tại",id);
            return null;
        }
        logger.info(account+"");
        var accountResponse = FindAccountResponse.builder()
                .id(id)
                .name(account.getName())
                .age(account.getAge())
                .date(account.getDate())
                .build();
        return accountResponse;
    }

    public List<FindAccountResponse> findAll() {
        logger.info("Chạy chương trình findAll");
        List<FindAccountResponse> responseList = new ArrayList<>();
        List<Account> list = accountRepository.findAll();
        for (Account account : list) {
            var response = FindAccountResponse.builder()
                    .id(account.getId())
                    .age(account.getAge())
                    .name(account.getName())
                    .date(account.getDate())
                    .build();
            responseList.add(response);
        }
        return responseList;
    }

    public UpdateAccountResponse updateAccount(String id, UpdateAccountRequest updateAccountRequest) {
        logger.info("Chạy chương trình {} với id = {}","updateAccount",id);
        var account = accountRepository.findById(id).orElse(null);
        if (account == null){
            logger.info("Update account:Tài khoản không tồn tại");
            return null;
        }
        var accountResponse = UpdateAccountResponse.builder()
                        .age(updateAccountRequest.getAge())
                        .name(updateAccountRequest.getName())
                        .date(updateAccountRequest.getDate())
                        .build();
        account.setDate(updateAccountRequest.getDate());
        account.setAge(updateAccountRequest.getAge());
        account.setName(updateAccountRequest.getName());
        accountRepository.save(account);
        return accountResponse;
    }

    public Account deleteAccount(String id){
        logger.info("Chạy chương trình {} với id = {}","deleteAccount",id);
        var acc = accountRepository.findById(id).orElse(null);
        if(acc == null){
            logger.info("Chạy chương trình {}: Không tồn tại user với id={}","updateAccount",id);
            return null;
        }
        accountRepository.deleteById(id);
        return acc;
    }
}
