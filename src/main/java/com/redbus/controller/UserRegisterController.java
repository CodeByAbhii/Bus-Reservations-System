package com.redbus.controller;


import com.redbus.entity.UserRegister;
import com.redbus.service.UserRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRegisterController {


    @Autowired
    private UserRegService userRegService;


    @PostMapping
    public String createuser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("profilePicture") MultipartFile profilePicture
    ) {

        try {
            UserRegister user = new UserRegister();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setProfilePicture(profilePicture.getBytes());

            userRegService.creteUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "profile Successfully Uploaded";
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserRegister> getUserById(@PathVariable long id){
        UserRegister userById = userRegService.getUserById(id);
        return  new ResponseEntity<>(userById , HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<UserRegister>> getAllUser(){
       List<UserRegister> userRegister = userRegService.getAllUSer();
       return new ResponseEntity<>(userRegister , HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<?> DeleteById(@PathVariable long id){
       userRegService.deleteById(id);
       return new ResponseEntity<>("Delete By Id Succesfully " , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRegister> updateUsers(@PathVariable long id , @RequestBody UserRegister userRegister){
       UserRegister userRegister1 = userRegService.updateUsers(id , userRegister);
       return new ResponseEntity<>(userRegister1 , HttpStatus.OK);
    }
}