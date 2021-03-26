package com.alinadev.repository.hibernate;

import com.alinadev.model.User;
import com.alinadev.repository.UserRepository;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by: Alina Ditu
 * Date: 5/14/17
 */
@Repository
public class UserHibernateRepository extends AbstractDao<Integer, User> implements UserRepository {

    @Override
    public List<User> findAll() {
        return retrieveAll();
    }

    @Override
    public User findById(int id) {
        return getByKey(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("email", email));

        User user = (User) crit.uniqueResult();

        return Optional.ofNullable(user);
    }

    @Override
    public void insert(User user) {
        persist(user);
    }

    @Override
    public void delete(int id) {
        User user = getByKey(id);
        delete(user);
    }

    @Override
    public List<User> findAllStudents() {
        Criteria criteria = createEntityCriteria();
        criteria.createAlias("userProfiles", "profile");
        criteria.add(Restrictions.ne("profile.type", "ADMIN"));

        return criteria.list();
    }

    @Override
    public void deleteBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        User user = (User)crit.uniqueResult();
        delete(user);
    }

    @Override
    public User findBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        User user = (User)crit.uniqueResult();
        if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }
}
