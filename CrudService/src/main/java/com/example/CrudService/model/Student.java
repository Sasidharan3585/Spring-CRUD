package com.example.CrudService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Student {
	
	@Id
	private String studentId;
	@Column
	private String studentName;
	@Column
	private String course;
	@Column
	private String dept;

}
