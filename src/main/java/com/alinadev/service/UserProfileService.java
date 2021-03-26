package com.alinadev.service;

import com.alinadev.model.UserProfile;

import java.util.List;

/**
 * Created by: Alina Ditu
 * Date: 6/11/17
 */
public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}
