package org.hibernateBoard.repository.user;

import org.hibernateBoard.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserIdAndUserPw(String userId, String userPw);
	
	public User findByUserEmail(String userEmail);
}
