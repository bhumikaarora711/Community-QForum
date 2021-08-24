package com.nagarro.repository;

/*
 * @author Bhumika_arora 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.models.User;
/*
 * This interface extends Spring Data JPA JpaRepository to interact with Database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public com.nagarro.models.User findUserByEmail(String email);
}
