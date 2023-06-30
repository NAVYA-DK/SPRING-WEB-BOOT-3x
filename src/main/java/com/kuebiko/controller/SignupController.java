package com.kuebiko.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuebiko.dao.entity.SignupEntity;
import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.SignupService;

@Controller
public class SignupController {
	
	@Autowired
	private SignupService signupService;
	
  @GetMapping("/deleteData")
	public String deleteSignup(@RequestParam int sid) {
		//WRITE LOGIC
	  signupService.deleteBySid(sid);
		return "redirect:/showData";
	}

		
		@GetMapping("/showData")
		public String showSignups(Model model,HttpSession session) {
			
			//Fetching data from session
			SignupDTO signupDTO=(SignupDTO)session.getAttribute("userLoggedIn");
			if(signupDTO==null) {
				 model.addAttribute("message","It seems like session is expired!");
				 return "login";
			}else {
				String currentRole=signupDTO.getRole();
				if(currentRole.equals("Admin")) {
					List<SignupDTO>  signupDTOs=signupService.findAllByRole("Customer");
					model.addAttribute("bananas", signupDTOs);
				}else {
					Optional<SignupDTO> optional=signupService.findByEmail(signupDTO.getEmail());
					List<SignupDTO> dtos=new ArrayList<SignupDTO>();
					dtos.add(optional.get());
					model.addAttribute("bananas", dtos);
				}
				 return "signups";
			}
		}
	
	@PostMapping("/signup")
	public String createSignup(@ModelAttribute SignupDTO signupDTO,Model model) {
		    //below method will save data inside database
		     if(signupService.findByEmail(signupDTO.getEmail()).isPresent()) {
				    model.addAttribute("message", "Sorry this email "+signupDTO.getEmail()+" already exits...");
				    return "signup";
			 }
	  	   
		    signupService.persist(signupDTO);
		    model.addAttribute("signupDTO",new SignupDTO());
			model.addAttribute("message","Ahaha DOne!!");
			return "signup";
	}
	
	
	@GetMapping("changePassword")
	public String showChangePassword() {
		  return "changePassword";
	}
	
	@PostMapping("changePassword")
	public String postChangePassword(String email,String newPassword , String confirmPassword, Model model) {
		if(!signupService.findByEmail(email).isPresent()) {
			model.addAttribute("message","Sorry, this email is not valid.");
			  return "changePassword";
		}else if(newPassword!=null && !newPassword.equals(confirmPassword)) {
			model.addAttribute("message","New password and confirm password does not match.");
			  return "changePassword";
		}
		signupService.updatePasswordByEmail(email, newPassword);
		model.addAttribute("message","Your new password is updated successfully..");
		  return "login";
	}
	
	@GetMapping({"/signup","/","/cool","/fool"})
	public String showSignup() {
		 //  /WEB-INF/login.jsp
		  return "signup";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,Model model) {
		  //Fetching signup dto from session
		  SignupDTO signupDTO=(SignupDTO)session.getAttribute("userLoggedIn");
		  
		   if(signupDTO!=null && signupDTO.getHid()!=0) {
			   //Fetching history id for that login
			   signupService.updateLogoutTime(signupDTO.getHid());   
		   }
		     session.invalidate();
			 model.addAttribute("message","Ahahah! you have been successfully! logout.");
		     return "login";
	}

	@GetMapping("/slogin")
	public String showLogin() {
		 //  /WEB-INF/login.jsp
		  return "login";
	}
	
	/**
	 *   <input type="text" name="username" class="form-control">
  	            <label>Password</label>
  	            <input type="password" name="password" class="form-control">
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@PostMapping("/auth")
	public String postLogin(@RequestParam String username, @RequestParam String password,Model pravat,HttpSession session) {
		Optional<SignupDTO> optional=signupService.findByEmailAndPassword(username,password);
		if(optional.isPresent()) {
			//Hey user is there
			//Create session object and add user details
			session.setMaxInactiveInterval(120);
			SignupDTO signupDTO =optional .get();
			session.setAttribute("userLoggedIn",signupDTO);
			int hid=signupService.saveLoginHistory(signupDTO.getSid());
			signupDTO.setHid(hid);
			return "redirect:/showData";
		}else {
			pravat.addAttribute("message", "Hmmm I hate you!");
		}
		  return "login";
	}
	
}