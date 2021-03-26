package com.alinadev.service.impl;

import com.alinadev.model.UserAttempts;
import com.alinadev.repository.UserAttemptsRepository;
import com.alinadev.service.UserAttemptsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

/**
 * Created by: Alina Ditu
 * Date: 7/5/17
 */
@Service
@Transactional
public class UserAttemptsServiceImpl implements UserAttemptsService {

    @Resource
    private UserAttemptsRepository userAttemptsRepository;

    public void resetUserAttempts(String username) {
        Optional<UserAttempts> userAttempts = userAttemptsRepository.findBySSO(username) ;

        if(userAttempts.isPresent()) {
            userAttempts.get().setAttempts(0);
            userAttemptsRepository.update(userAttempts.get());
        }

    }

    public void updateFailAttempts(String username) {
        Optional<UserAttempts> attemptsOptional = userAttemptsRepository.findBySSO(username) ;

        if(attemptsOptional.isPresent()) {
            UserAttempts userAttempts = attemptsOptional.get();
            Integer attempts = userAttempts.getAttempts();
            attempts++;
            userAttempts.setAttempts(attempts);
            userAttempts.setLastModified(new Date());
            userAttemptsRepository.update(userAttempts);
        }  else{
            UserAttempts userAttempts = new UserAttempts();
            userAttempts.setSsoId(username);
            userAttempts.setAttempts(1);
            userAttempts.setLastModified(new Date());
            userAttemptsRepository.persist(userAttempts);
        }
    }

    @Override
    public Optional<UserAttempts> retrieveByUsername(String username) {
        return userAttemptsRepository.findBySSO(username);
    }
}
