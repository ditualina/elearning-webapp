package com.alinadev.repository.hibernate;

import com.alinadev.model.Course;
import com.alinadev.repository.CourseRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class CourseHibernateRepository extends AbstractDao<Integer, Course>
        implements CourseRepository {


    @Override
    public List<Course> findAll() {
        return retrieveAll();
    }

    @Override
    public Course findById(int id) {
        Course course = getByKey(id);
        return course;
    }

    @Override
    public void insert(Course course) {
        persist(course);
    }

    @Override
    public void delete(int id) {
        Course course = getByKey(id);
        if (course != null) {
            delete(course);
        }
    }

    @Override
    public List<Course> findAllOverdue() {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.le("dueDate", new Date()));

        List<Course> courseList = crit.list();
        if (CollectionUtils.isEmpty(courseList)) {
            return Collections.EMPTY_LIST;
        }
        return courseList;
    }

    @Override
    public List<Course> findAllNext3Days() {
        Criteria crit = createEntityCriteria();
        Date upcomingDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(upcomingDate);
        c.add(Calendar.DATE, 1);
        upcomingDate = c.getTime();

        crit.add(Restrictions.between("dueDate", new Date(), upcomingDate));

        List<Course> courseList = crit.list();
        if (CollectionUtils.isEmpty(courseList)) {
            return Collections.EMPTY_LIST;
        }
        return courseList;
    }
}
