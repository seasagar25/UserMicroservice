/**
 * 
 */
package com.user.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.CreateUserResponseModel;
import com.user.model.UserDTO;
import com.user.model.UserModel;
import com.user.service.UserService;

/**
 * @author sagar
 *
 */
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserRestController {

	@Autowired
	private Environment env;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/status/check")
	public String getStatus() {
		return "working on port--"+env.getProperty("local.server.port")+" with token-"+env.getProperty("security.token");	
	}
	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody UserModel userModel) {
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDTO userDto = mapper.map(userModel, UserDTO.class);
		UserDTO createdUser = userService.createUser(userDto);
		CreateUserResponseModel response = mapper.map(createdUser, CreateUserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
