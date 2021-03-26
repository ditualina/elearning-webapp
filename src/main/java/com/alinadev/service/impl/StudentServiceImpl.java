package com.alinadev.service.impl;

import com.alinadev.model.Student;
import com.alinadev.model.User;
import com.alinadev.repository.StudentRepository;
import com.alinadev.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * Created by: Alina Ditu
 * Date: 6/7/17
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final String SAVE_DIRECTORY_PATH =
            "E:\\Study\\Automatica\\Master\\Disertatie\\ELearningApplication\\WebContent\\userData\\";

    @Resource
    private StudentRepository studentRepository;

    @Override
    public void addStudent(Student student) {
        studentRepository.insert(student);

        new File(
                "E:\\Study\\Automatica\\Anul IV\\Licenta\\licenta\\WebContent\\userData\\"
                        + student.getFirstName() + student.getLastName())
                .mkdir();
    }

    @Override
    public void createStudent(User user, Integer scholarYear) {
        Student student = new Student();
        student.setFirstName(user.getFirstName());
        student.setLastName(user.getLastName());
        student.setEmail(user.getEmail());
        student.setScholarYear(scholarYear);
        student.setUser(user);

        //create new folder with ssoId name for each student
        new File(SAVE_DIRECTORY_PATH + user.getSsoId()).mkdir();

        studentRepository.insert(student);
    }

    @Override
    public Optional<Student> findByUser(Integer userId) {
           return studentRepository.findByUser(userId);
    }

    @Override
    public List<Student> retrieveAll() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student)  {
        Student persistedStudent = studentRepository.findById(student.getId());

        persistedStudent.setScholarYear(student.getScholarYear());
        persistedStudent.setDepartment(student.getDepartment());
        persistedStudent.setAddress(student.getAddress());
        persistedStudent.setGender(student.getGender());
        persistedStudent.setPhoneNumber(student.getPhoneNumber());
        persistedStudent.setEmail(student.getEmail());

        studentRepository.update(persistedStudent);

        return persistedStudent;
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }
}
