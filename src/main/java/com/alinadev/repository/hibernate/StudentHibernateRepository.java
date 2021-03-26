package com.alinadev.repository.hibernate;

import com.alinadev.model.Student;
import com.alinadev.repository.StudentRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentHibernateRepository extends
		AbstractDao<Integer,Student> implements StudentRepository {


    @Override
    public List<Student> findAll() {
        return retrieveAll();
    }

    @Override
    public Student findById(int id) {
        Student student = getByKey(id);
        return student;
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("email", email));

        Student student = (Student) crit.uniqueResult();

        return Optional.ofNullable(student);
    }

    @Override
    public Optional<Student> findByUser(Integer userId) {
        Criteria crit = createEntityCriteria();
        crit.createAlias("user", "usr");
        crit.add(Restrictions.eq("usr.id", userId));

        Student student = (Student) crit.uniqueResult();

        return Optional.ofNullable(student);
    }

    @Override
    public void insert(Student student) {
         persist(student);
    }

    @Override
    public void delete(int id) {
        Student student = getByKey(id);
        if(student != null) {
            delete(student);
        }
    }
}
