package com.alinadev.repository;

import java.util.List;

import com.alinadev.model.Course;

public interface CourseRepository {
	List<Course> findAll();

	Course findById(int id);
	
	void insert(Course course);

	void delete(int id);

	void update(Course course);

    List<Course> findAllOverdue();

    public List<Course> findAllNext3Days();
}
