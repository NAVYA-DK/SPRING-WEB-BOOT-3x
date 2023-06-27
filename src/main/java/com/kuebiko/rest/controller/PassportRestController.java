package com.kuebiko.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuebiko.dto.PassportDTO;
import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.PassportService;
import com.kuebiko.service.SignupService;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*") // CORS - Cross CrossOrigin Resource Sharing
public class PassportRestController {
	
	@Autowired
	private PassportService passportService;
	
	
	
	@GetMapping("/signups/{sid}/passport")
	public PassportDTO getPassport(@PathVariable("sid")int sid) {
		//WRITE LOGIC
			PassportDTO passportDTO=passportService.findBySignupId(sid);
			return  passportDTO;
	}
	
}
