package com.alinadev.repository.hibernate;

import com.alinadev.model.UserAttempts;
import com.alinadev.repository.UserAttemptsRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by: Alina Ditu
 * Date: 7/2/17
 */
@Repository
public class UserAttemptsHibernateRepository extends AbstractDao<Integer, UserAttempts>
        implements UserAttemptsRepository {

    @Override
    public Optional<UserAttempts> findBySSO(String ssoId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", ssoId));

        UserAttempts user = (UserAttempts) crit.uniqueResult();

        return Optional.ofNullable(user);
    }
}
