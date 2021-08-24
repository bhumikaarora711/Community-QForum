package com.nagarro.repository;

/*
 * @author Bhumika_arora 
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.nagarro.models.Questions;

/*
 * This interface extends Spring Data JPA JpaRepository to interact with Database.
 */
public interface QuestionRepository extends JpaRepository<Questions, Integer>, JpaSpecificationExecutor<Questions> {

	List<Questions> findQuestionByTitle(String text);

	List<Questions> findByTitleContaining(String title);

	List<Questions> findByAuthorContaining(String author);

	List<Questions> findByCategoryContaining(String category);

	List<Questions> findByBodyContaining(String text);

	List<Questions> findByCategoryCodeContaining(String text);

	List<Questions> findByInstantContaining(String date);
}
