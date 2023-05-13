package com.example.CrudService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CrudService.exceptions.StudentIdAlreadyExistsException;
import com.example.CrudService.model.Student;
import com.example.CrudService.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")

public class StudentController {
	@Autowired
	public  StudentService service;
	
@PostMapping("/add")
public ResponseEntity<?> addStudent(@RequestBody Student student) {
	ResponseEntity<?> entity= null;
	try {
		Student obj= service.addStudent(student);
		entity= new ResponseEntity<String>("Student created successfully",HttpStatus.OK);
	} catch (StudentIdAlreadyExistsException e) {
		entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);  
	}
	
	return entity;
}


@GetMapping("/all")
public ResponseEntity<?> getAllStudents(){
	List<Student> list= service.getAllStudents();
	ResponseEntity<?> entity= new ResponseEntity<List<Student>>(list,HttpStatus.OK);
	return entity;
}

@GetMapping("/id/{studentId}")
public ResponseEntity<?> getOneStudent(@PathVariable String studentId){
	ResponseEntity<?> entity= new ResponseEntity<String>("Student Id does not exist",HttpStatus.BAD_REQUEST);
	Optional<Student> opt= service.getOneStudent(studentId);
	Student obj=opt.get();
	if (opt != null) {
		entity = new ResponseEntity<Student>(obj, HttpStatus.OK);
	}
	return entity;
		
}

@PutMapping("/modify/{studentId}")
public ResponseEntity<?> updateStudent(@PathVariable String studentId,@RequestBody Student student){
	ResponseEntity<?> entity= new ResponseEntity<String>("Operation Failed",HttpStatus.BAD_REQUEST);
	boolean isChanged= service.modifyStudent(studentId, student);
	if(isChanged) {
		entity= new ResponseEntity<String>("Student with id "+studentId+ " updated",HttpStatus.OK);
		
	}
	return entity;
	
	
}

@DeleteMapping("/remove/{studentId}")
public ResponseEntity<?> deleteStudent(@PathVariable String studentId){
	ResponseEntity<?> entity= new ResponseEntity<String>("Invalid studentid",HttpStatus.BAD_REQUEST);
	boolean isEmpty=service.deleteStudentById(studentId);
	if(isEmpty) {
		entity= new ResponseEntity<String>("Student with id "+studentId+"  deleted",HttpStatus.OK);
	}
	return entity;
	
}








}
