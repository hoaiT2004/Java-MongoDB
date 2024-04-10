package com.example.springbootwithmongo.controller;

import com.example.springbootwithmongo.model.RegisterAccountRequest;
import com.example.springbootwithmongo.model.UpdateAccountRequest;
import com.example.springbootwithmongo.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@NotEmpty(message = "Thiếu id") @PathVariable(name = "id") String id) throws Exception {
//        if(bindingResult.hasErrors()){
//         //   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The error value of variables");
//            throw new Exception("Sai rồi bạn");
//        }
        System.out.println("Id = "+id);
        var account = accountService.findById(id);
        if(account != null) return ResponseEntity.status(HttpStatus.OK).body(account);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found User with id="+id);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertAccount(@Valid @RequestBody RegisterAccountRequest request,BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The error value of variables");
            throw new Exception("Sai rồi bạn!");
        }
        System.out.println("Object="+request);
        var accountResponse = accountService.registerAccount(request);
        if(accountResponse == null) return ResponseEntity.status(HttpStatus.CREATED).body("Existed");
        return ResponseEntity.status(HttpStatus.OK).body(accountResponse);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(BindingResult bindingResult,@Valid @RequestBody UpdateAccountRequest request, @PathVariable String id) throws Exception {
        if(bindingResult.hasErrors()){
       //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The error value of variables");
            throw new Exception("Sai rồi bạn");
        }
        System.out.println("Object="+request);
        var accountResponse = accountService.updateAccount(id,request);
        if(accountResponse==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account doesn't exist with id="+id);
        return ResponseEntity.status(HttpStatus.OK).body(accountResponse);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@NotEmpty(message = "Thiếu id") @PathVariable String id) throws Exception {
//        if(bindingResult.hasErrors()){
//       //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The error value of variables");
//            throw new Exception("Sai rồi bạn");
//        }
        var account = accountService.deleteAccount(id);
        if(account==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account doesn't exist with id="+id);
        return ResponseEntity.status(HttpStatus.OK).body(account+"\nDelete Successfully!");
    }
}
