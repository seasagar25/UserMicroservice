/**
 * 
 */
package com.user.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.model.UserDTO;
import com.user.model.UserEntity;
import com.user.repository.UserRepository;

/**
 * @author sagar
 *
 */
@Service
public class UserServiceImpl implements UserService {


	
	UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDTO createUser(UserDTO userModel) {
		userModel.setUserId(UUID.randomUUID().toString());
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity entity = mapper.map(userModel, UserEntity.class);
		entity.setEncryptPassword("abcd");
		userRepository.save(entity);
		UserDTO entity1 = mapper.map(entity, UserDTO.class);
		return entity1;
	}

}
