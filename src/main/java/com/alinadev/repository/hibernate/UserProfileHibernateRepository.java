package com.alinadev.repository.hibernate;

import com.alinadev.model.UserProfile;
import com.alinadev.repository.UserProfileRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by: Alina Ditu
 * Date: 6/7/17
 */
@Repository
public class UserProfileHibernateRepository  extends AbstractDao<Integer, UserProfile>implements UserProfileRepository{

    public UserProfile findById(int id) {
        return getByKey(id);
    }

    public UserProfile findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (UserProfile) crit.uniqueResult();
    }

    @Override
    public void saveProfile(UserProfile userProfile) {
        persist(userProfile);
    }

    @SuppressWarnings("unchecked")
    public List<UserProfile> findAll(){
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<UserProfile>)crit.list();
    }

}

