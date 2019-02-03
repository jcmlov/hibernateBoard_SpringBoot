package org.hibernateBoard.security.service;

import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.entity.user.UserConnection;
import org.hibernateBoard.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private UserRepository userRepository;

    public User signUp(UserConnection userConnection) {
    	User user = User.signUp(userConnection);
    	
        return userRepository.save(user);
    }

    public User findBySocial(UserConnection userConnection) {
        User user = userRepository.findBySocial(userConnection);
        if (user == null) throw new RuntimeException();
        
        return user;
    }

    public boolean isExistUser(UserConnection userConnection) {
    	boolean result = false;
    	
        User user = userRepository.findBySocial(userConnection);
        
        if(user != null) {
        	result = true;
        }
        
        return result;
    }

}
