package com.nagarro.controller;
/*
 * @author Bhumika_arora
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.models.Category;
import com.nagarro.models.Questions;
import com.nagarro.repository.CategoryRepository;
import com.nagarro.repository.QuestionRepository;
import com.sipios.springsearch.anotation.SearchSpec;

@CrossOrigin(origins = "*")
@RestController
public class QuestionController {

	private static final String SEARCH = "/search";
	private static final String POSTCATEGORY = "/postcategory";
	private static final String GETCATEGORY = "/getcategory";
	private static final String CLOSEDQUE_ID = "/closedque/{id}";
	private static final String SEARCHQUE = "/searchque";
	private static final String GETQUEBYID_ID = "/getquebyid/{id}";
	private static final String POSTQUE = "/postque";
	private static final String GETQUE = "/getque";
	private static final String GETQUESCOUNT = "/getquecount";
	
	@Autowired
	private QuestionRepository queRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@RequestMapping(value = GETQUE, method = RequestMethod.GET)
	public List<Questions> getQuestions() {
		return this.queRepo.findAll();
	}

	@RequestMapping(value = POSTQUE, method = RequestMethod.POST)
	public Questions postQuestions(@RequestBody final Questions que) {
		return this.queRepo.save(que);
	}

	@RequestMapping(value = GETQUEBYID_ID, method = RequestMethod.GET)
	public Optional<Questions> getQuestionById(@PathVariable final int id) {
		return this.queRepo.findById(id);
	}

	@RequestMapping(value = SEARCHQUE, method = RequestMethod.GET)
	public ResponseEntity<List<Questions>> getAllQuestions(@RequestParam(required = false) final String title) {
		try {
			final List<Questions> questions = new ArrayList<Questions>();
			final List<Questions> newList = new ArrayList<Questions>();
			if (title == null)
				queRepo.findAll().forEach(questions::add);
			else {
				queRepo.findByTitleContaining(title).forEach(questions::add);
				queRepo.findByAuthorContaining(title).forEach(questions::add);
				queRepo.findByCategoryContaining(title).forEach(questions::add);
				queRepo.findByCategoryCodeContaining(title).forEach(questions::add);
				queRepo.findByInstantContaining(title).forEach(questions::add);
				
				for (final Questions que : questions) {
					if (!newList.contains(que)) {
						newList.add(que);
					}
				}
			}

			if (newList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(newList, HttpStatus.OK);
		} catch (final Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(CLOSEDQUE_ID)
	public ResponseEntity<Questions> updateQuestion(@PathVariable("id") final int id) {
		final Optional<Questions> quesData = queRepo.findById(id);

		if (quesData.isPresent()) {
			final Questions que = quesData.get();
			if (que.isQue_closed() == false) {
				que.setQue_closed(true);
			} else {
				que.setQue_closed(false);
			}
			return new ResponseEntity<>(queRepo.save(que), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = GETCATEGORY, method = RequestMethod.GET)
	public List<Category> getCategory() {
		return this.categoryRepo.findAll();
	}

	@RequestMapping(value = POSTCATEGORY, method = RequestMethod.POST)
	public Category postCategory(@RequestBody final Category category) {
		return this.categoryRepo.save(category);
	}

	@RequestMapping(value = SEARCH, method = RequestMethod.GET)
	public ResponseEntity<List<Questions>> getQues(@SearchSpec final Specification<Questions> specs) {
		return new ResponseEntity<>(queRepo.findAll(Specification.where(specs)), HttpStatus.OK);

	}
	@RequestMapping(value = GETQUESCOUNT, method = RequestMethod.GET)
	public  int getQueCount() {
		int count=0;
		List<Questions> que= this.queRepo.findAll();
		for(@SuppressWarnings("unused") Questions element:que) {
			count++;
		}
		return count;
	}

}
