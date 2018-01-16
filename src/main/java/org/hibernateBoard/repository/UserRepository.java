package org.hibernateBoard.repository;

import org.hibernateBoard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}
