package com.alinadev.repository;

import com.alinadev.model.UserProfile;

import java.util.List;

/**
 * Created by: Alina Ditu
 * Date: 6/7/17
 */
public interface UserProfileRepository {

    void saveProfile(UserProfile userProfile);

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
