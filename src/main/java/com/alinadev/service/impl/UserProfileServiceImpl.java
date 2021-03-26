package com.alinadev.service.impl;

import com.alinadev.model.UserProfile;
import com.alinadev.repository.UserProfileRepository;
import com.alinadev.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by: Alina Ditu
 * Date: 6/11/17
 */

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository repository;

    public UserProfile findById(int id) {
        return repository.findById(id);
    }

    public UserProfile findByType(String type){
        return repository.findByType(type);
    }

    public List<UserProfile> findAll() {
        return repository.findAll();
    }
}
