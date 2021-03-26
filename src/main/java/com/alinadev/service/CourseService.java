package com.alinadev.service;

import com.alinadev.model.Course;
import com.alinadev.repository.CourseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {

    @Resource
	private CourseRepository courseRepository;

    public List<Course> retrieveaAll()  {
        return courseRepository.findAll();
    }

    public Optional<Course> retrieveById(int id) {
        Course course = courseRepository.findById(id);

        return Optional.ofNullable(course);
    }

    public List<Course> retrieveOverdue() {
         return courseRepository.findAllOverdue();
    }

    public List<Course> findAllNext3Days() {
        return courseRepository.findAllNext3Days();
    }
}
