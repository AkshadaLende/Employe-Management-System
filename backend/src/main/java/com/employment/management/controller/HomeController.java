package com.employment.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employment.management.entity.RegisterRequest;
import com.employment.management.model.Employe;
import com.employment.management.service.EmployeService;


@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class HomeController {
	@Autowired
	EmployeService employeService;

	/*
	 * @RequestMapping("/index") public ModelAndView view(HttpRequest httpRequest) {
	 * ModelAndView mv= new ModelAndView(); mv.setView("") return mv; }
	 */
	/*
	 * @GetMapping("/") public String home() { return "ndfjek"; }
	 */
	
	
	@PostMapping("/AddData")
	public ResponseEntity addData(@RequestBody Employe employe) {
		System.out.println(employe);
		employeService.store(employe);
		return new ResponseEntity(HttpStatus.OK);
	}
	@PostMapping("/AllData")
	public List<Employe> AllData() {
		List<Employe> list=employeService.getAllData();
		return list;
	}
	@PostMapping("/UpdateData")
	public ResponseEntity updateData(@RequestBody RegisterRequest registerRequest) {
		employeService.update(registerRequest);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	@PostMapping("/DeleteData/{id}")
	public ResponseEntity deleteData(@PathVariable Integer id) {
		employeService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> employeById(@PathVariable Integer id) {
		//employeService.employeById(id);
		return new ResponseEntity(employeService.employeById(id),HttpStatus.OK);
	}
	
	
}
