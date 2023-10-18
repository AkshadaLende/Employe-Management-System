package com.employment.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employment.management.entity.RegisterRequest;
import com.employment.management.model.Employe;
import com.employment.management.repository.EmployeRepository;
@Service
public class EmployeService {
    @Autowired
	EmployeRepository employeRepository;
	public void store(Employe employe) {
		// TODO Auto-generated method stub
		employeRepository.save(employe);
	}
	public List<Employe> getAllData() {
		// TODO Auto-generated method stub
		List<Employe> list=employeRepository.findAll();
		return list;
	}
	public void update(RegisterRequest registerRequest) {
		// TODO Auto-generated method stub
		
	Employe employe=employeRepository.findById( (long) registerRequest.getId()).orElse(null);
		if(employe != null) {
			employe.setAddress(registerRequest.getAddress());
			employe.setName(registerRequest.getName());
			employe.setMobileno(registerRequest.getMobileno());
			employe.setAdharnumber(registerRequest.getAdharnumber());
			employeRepository.save(employe);
		}
		
	}
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		employeRepository.deleteById((long) id);
	}
	//@SuppressWarnings("deprecation")
	public Employe employeById(Integer id) {
		// TODO Auto-generated method stub
		return	employeRepository.findById((long) id).get();
		 
	}
 
}
