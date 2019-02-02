package org.hibernateBoard.security.service;

import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.repository.user.UserRepository;
import org.hibernateBoard.security.userConnection.UserConnection;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private UserRepository userRepository;

    public User signUp(UserConnection userConnection) {
        final User user = User.signUp(userConnection);
        return userRepository.save(user);
    }

    public User findBySocial(UserConnection userConnection) {
        final User user = userRepository.findBySocial(userConnection);
        if (user == null) throw new RuntimeException();
        return user;
    }

    public boolean isExistUser(UserConnection userConnection) {
        final User user = userRepository.findBySocial(userConnection);
        return (user != null);
    }

}
