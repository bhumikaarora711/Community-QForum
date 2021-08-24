package com.nagarro.controller;

/*
 * @author Bhumika_arora 
 */
import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.models.*;
import com.nagarro.repository.*;


@CrossOrigin(origins = "*")
@RestController
public class AnswerController {

	private static final String POSTCOMMENT = "/postcomment";
	private static final String MARKANS_ID = "/markans/{id}";
	private static final String POSTANS = "/postans";
	private static final String GETANS = "/getans";

	@Autowired
	private AnswerRepository ansRepo;

	@Autowired
	private ContactRepository contactRepo;

	@RequestMapping(value = GETANS, method = RequestMethod.GET)
	public List<Answers> getAnswers() {
		return this.ansRepo.findAll();
	}

	@RequestMapping(value = POSTANS, method = RequestMethod.POST)
	public Answers postAnswer(@RequestBody final Answers ans) {
		return this.ansRepo.save(ans);

	}

	@PutMapping(MARKANS_ID)
	public ResponseEntity<Answers> updateAnswer(@PathVariable("id") final int id) {
		final Optional<Answers> ans = ansRepo.findById(id);

		if (ans.isPresent()) {
			final Answers setAns = ans.get();
			if (setAns.isCorrect_ans() == false) {
				setAns.setCorrect_ans(true);
			} else {
				setAns.setCorrect_ans(false);
			}
			return new ResponseEntity<>(ansRepo.save(setAns), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = POSTCOMMENT, method = RequestMethod.POST)
	public Contacts postContact(@RequestBody final Contacts contact) {
		return this.contactRepo.save(contact);
	}

}
