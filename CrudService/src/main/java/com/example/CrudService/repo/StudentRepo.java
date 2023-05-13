package com.example.CrudService.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CrudService.model.Student;

@Transactional
@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
	
	

}
