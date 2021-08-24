package com.nagarro.repository;
/*
 * @author Bhumika_arora 
 */
import org.springframework.data.jpa.repository.JpaRepository; 

import com.nagarro.models.Answers;
/*
 * This interface extends Spring Data JPA JpaRepository to interact with Database.
 */
public interface AnswerRepository extends JpaRepository<Answers, Integer> {

}
