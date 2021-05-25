package com.ftn.uns.ac.rs.theperfectmeal.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.theperfectmeal.dto.UserDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Admin;
import com.ftn.uns.ac.rs.theperfectmeal.model.RegisteredUser;
import com.ftn.uns.ac.rs.theperfectmeal.model.User;



@Component
public class UserMapper implements MapperInterface<User, UserDTO> {
	
    @Override
    public User toEntity(UserDTO dto) {
        return new User(dto.getFirstName(), dto.getLastName(), 
        				dto.getEmail(), dto.getPassword());
    }
    
    

    @Override
    public UserDTO toDto(User entity) {
        return new UserDTO(entity.getId(), entity.getFirstName(), entity.getLastName(),
        				   entity.getEmail(), entity.getPassword());
    }
    
    public List<UserDTO> toUserDTOList(List<User> users){
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user: users) {
            userDTOS.add(toDto(user));
        }
        return userDTOS;
    }
    
    public ArrayList<UserDTO> toUserDTORegUserList(List<RegisteredUser> users){
    	
    	ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (RegisteredUser user: users) {
            userDTOS.add(toDto(user));
        }
        return userDTOS;
    }
    
    public List<UserDTO> toUserDTOAdminList(List<Admin> users){
        List<UserDTO> userDTOS = new ArrayList<>();
        for (Admin user: users) {
            userDTOS.add(toDto(user));
        }
        return userDTOS;
    }
}
