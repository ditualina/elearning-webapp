package com.alinadev.repository;

import java.util.List;
import java.util.Optional;

import com.alinadev.model.Student;
import com.alinadev.model.User;

public interface StudentRepository {
	List<Student> findAll();

	Student findById(int id);
	
	Optional<Student> findByEmail(String email);

    Optional<Student> findByUser(Integer userId);

	void insert(Student student);

	void delete(int id);

	void update(Student student); 
}
