package com.alinadev.service;

import com.alinadev.model.UserAttempts;

import java.util.Optional;

/**
 * Created by: Alina Ditu
 * Date: 7/5/17
 */
public interface UserAttemptsService {

    public void resetUserAttempts(String username);

    public void updateFailAttempts(String username);

    public Optional<UserAttempts> retrieveByUsername(String username);
}
