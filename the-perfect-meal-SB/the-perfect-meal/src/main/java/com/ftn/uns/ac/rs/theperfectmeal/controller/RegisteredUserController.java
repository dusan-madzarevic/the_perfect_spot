package com.ftn.uns.ac.rs.theperfectmeal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.theperfectmeal.dto.UserDTO;
import com.ftn.uns.ac.rs.theperfectmeal.helper.RegisteredUserMapper;
import com.ftn.uns.ac.rs.theperfectmeal.helper.UserMapper;
import com.ftn.uns.ac.rs.theperfectmeal.model.RegisteredUser;
import com.ftn.uns.ac.rs.theperfectmeal.model.User;
import com.ftn.uns.ac.rs.theperfectmeal.service.RegisteredUserService;



@RestController
@RequestMapping("/registeredUser")
public class RegisteredUserController {

	@Autowired
	private RegisteredUserService registeredUserService;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RegisteredUserMapper regUserMapper;
	
	

	@GetMapping()
	public ResponseEntity<ArrayList<UserDTO>> getAllRegisteredUsers() {
		List<RegisteredUser> users = registeredUserService.findAll();

		ArrayList<UserDTO> dtos = userMapper.toUserDTORegUserList(users);
		if(!users.isEmpty())
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
    @GetMapping(value = "/by-page/{pageNum}")
 	public ResponseEntity<Page<UserDTO>> getAll(@PathVariable int pageNum) {
    	Pageable pageable = PageRequest.of(pageNum, 10);
 		Page<RegisteredUser> page = registeredUserService.findAll(pageable);
 		List<UserDTO> userDTOS = userMapper.toUserDTORegUserList(page.toList());
 		Page<UserDTO> pageUserDTOS = new PageImpl<>(userDTOS,
										 				page.getPageable(), 
										 				page.getTotalElements());

 		return new ResponseEntity<>(pageUserDTOS, HttpStatus.OK);
 	}

 
	
    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
    	System.out.println("getRegisteredUser with id = " + id);
        RegisteredUser user = registeredUserService.findOne(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    	System.out.println("getRegisteredUser " + user);

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }
    
    @RequestMapping(value="/byEmail/{email}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
        User user = registeredUserService.findOneByEmail(email);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        RegisteredUser user;
        try {
             user = registeredUserService.create(regUserMapper.toEntity(userDTO));
        } catch (Exception e) {
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.CREATED);
    }
	
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
    	RegisteredUser user;
        try {
            user = registeredUserService.update((RegisteredUser)userMapper.toEntity(userDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }
    

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try {
        	registeredUserService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
