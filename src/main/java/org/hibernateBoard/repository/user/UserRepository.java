package org.hibernateBoard.repository.user;

import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.security.userConnection.UserConnection;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	User findBySocial(UserConnection userConnection);
}
