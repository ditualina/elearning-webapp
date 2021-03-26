package com.alinadev.service;

import com.alinadev.model.Student;
import com.alinadev.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by: Alina Ditu
 * Date: 6/7/17
 */
public interface StudentService {

    public void addStudent(Student student);

    public void createStudent(User user, Integer scholarYear);

    public Optional<Student> findByUser(Integer userId);

    public List<Student> retrieveAll();

    public Student updateStudent(Student student);

    public Student findById(int id);
}
