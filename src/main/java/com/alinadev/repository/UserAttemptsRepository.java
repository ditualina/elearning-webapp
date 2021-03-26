package com.alinadev.repository;

import com.alinadev.model.UserAttempts;

import java.util.Optional;

/**
 * Created by: Alina Ditu
 * Date: 7/2/17
 */
public interface UserAttemptsRepository {

    Optional<UserAttempts> findBySSO(String ssoId);

    void update(UserAttempts userAttempts);

    void persist(UserAttempts userAttempts);
}
