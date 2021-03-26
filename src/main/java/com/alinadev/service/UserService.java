package com.alinadev.service;

import com.alinadev.model.Student;
import com.alinadev.model.User;
import com.alinadev.model.UserProfile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by: Alina Ditu
 * Date: 6/7/17
 */
public interface UserService {

    public Optional<Student> retrieveStudentByEmail(String email);

    public Optional<User> retrieveUserByEmail(String email);

    void addUserByEmail(String email, String password);

    User findBySSO(String sso);

    List<User> findAllUsers();

    List<User> findAllUsersBasedOnRole(Set<UserProfile> profile);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    boolean isUserSSOUnique(Integer id, String sso);
}
