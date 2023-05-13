package com.example.CrudService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudService.exceptions.StudentIdAlreadyExistsException;
import com.example.CrudService.model.Student;
import com.example.CrudService.repo.StudentRepo;

@Service
public class StudentService {
	@Autowired
	private StudentRepo studentRepo;

	public Student addStudent(Student student) throws StudentIdAlreadyExistsException {
		Student studentObj=null;
		Optional<Student> opt=studentRepo.findById(student.getStudentId());
		if(opt.isPresent()) {
			throw new StudentIdAlreadyExistsException("Student Id already exists");
			}
		studentObj=studentRepo.save(student);
		return studentObj;
	}

	public List<Student> getAllStudents() {
		List<Student> list=studentRepo.findAll();
		return list;
		
	}

	public Optional<Student> getOneStudent(String studentId) {
		Optional<Student> op=studentRepo.findById(studentId);
		Student obj=op.isPresent() ? op.get():null;
		
		return op;
	}

	public Student updateStudent(Student obj) {
	   studentRepo.save(obj);
	   return obj;
		
	}

	public boolean deleteStudentById(String studentId) {
		Optional<Student> opt=studentRepo.findById(studentId);
		boolean isEmpty=false;
		if(opt.isPresent()) {
			studentRepo.deleteById(studentId);
			isEmpty=true;
		}
		return isEmpty;
	}

	public boolean modifyStudent(String studentId,Student student) {
		boolean isChanged=false;
		Optional<Student>opt=studentRepo.findById(studentId);
		if(opt.isPresent()) {
			Student st=opt.get();
			st.setStudentId(student.getStudentId());
			st.setStudentName(student.getStudentName());
			st.setCourse(student.getCourse());
			st.setDept(student.getDept());
			studentRepo.save(st);
			isChanged=true;
		}
		return isChanged;
	}

}
