package org.hibernateBoard.repository.user;

import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.entity.user.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findBySocial(UserConnection userConnection);
}
