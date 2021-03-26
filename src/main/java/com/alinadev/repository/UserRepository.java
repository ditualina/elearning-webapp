package com.alinadev.repository;

import com.alinadev.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by: Alina Ditu
 * Date: 5/14/17
 */
public interface UserRepository {
    List<User> findAll();

    User findById(int id);

    Optional<User> findByEmail(String email);

    User findBySSO(String sso);

    void insert(User student);

    void delete(int id);

    void update(User student);

    List<User> findAllStudents();

    void deleteBySSO(String ssoId)  ;

}
