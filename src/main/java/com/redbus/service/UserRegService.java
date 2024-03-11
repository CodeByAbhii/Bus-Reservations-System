package com.redbus.service;


import com.redbus.entity.UserRegister;
import com.redbus.payload.UserRegisterDto;
import com.redbus.repository.UserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegService {


    @Autowired
    private UserRegisterRepository userRegisterRepository;


    public UserRegisterDto creteUser(UserRegister userRegister){
       userRegisterRepository.save(userRegister);
        return  null;
    }

    public UserRegister getUserById(long id) {
        UserRegister userRegister = userRegisterRepository.findById(id).get();
        return userRegister;
    }

    public List<UserRegister> getAllUSer() {
        List<UserRegister> userRegisters = userRegisterRepository.findAll();
        return userRegisters;
    }

    public String deleteById(long id) {
        userRegisterRepository.deleteById(id);
        return "Delete By Id Succesfully";
    }

    public UserRegister updateUsers(long id, UserRegister userRegister) {
        UserRegister userRegister1 = userRegisterRepository.findById(id).get();
        UserRegister users = new UserRegister();
        users.setName(userRegister.getName());
        users.setEmail(userRegister.getEmail());
        users.setPassword(userRegister.getPassword());
        users.setProfilePicture(userRegister.getProfilePicture());
        UserRegister save = userRegisterRepository.save(userRegister);
        return save;
    }
}
