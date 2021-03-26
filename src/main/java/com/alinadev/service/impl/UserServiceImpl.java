package com.alinadev.service.impl;

import com.alinadev.model.*;
import com.alinadev.repository.CourseRepository;
import com.alinadev.repository.StudentRepository;
import com.alinadev.repository.UserProfileRepository;
import com.alinadev.repository.UserRepository;
import com.alinadev.service.PasswordAuthentication;
import com.alinadev.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by: Alina Ditu
 * Date: 6/7/17
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    UserProfileRepository profileRepository;

    @Resource
    StudentRepository repository;

    @Resource
    UserRepository userRepository;

    @Resource
    CourseRepository courseRepository;

    @Resource
    StudentRepository studentRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Student> retrieveStudentByEmail(String email) {
        Optional<Student> studentOptional = repository.findByEmail(email);

        return studentOptional;
    }

    @Override
    public Optional<User> retrieveUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional;
    }

    @Override
    public void addUserByEmail(String email, String password) {
        User user = new User();

        UserProfile profile = new UserProfile();
        profileRepository.saveProfile(profile);

        user.getUserProfiles().add(profile);
        user.setEmail(email);

        try {
            user.setPassword(PasswordAuthentication.generateStorngPasswordHash(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        userRepository.insert(user);
    }

    public User findBySSO(String sso) {
        User user = userRepository.findBySSO(sso);
        return user;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllUsersBasedOnRole(Set<UserProfile> profiles) {
        Optional<UserProfile> profileOptional = profiles.stream().filter(prf -> prf.getType().equals(UserProfileType.ADMIN.name())).findAny();
        if (profileOptional.isPresent()) {
            return userRepository.findAll();
        } else {
            return userRepository.findAllStudents();
        }
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.insert(user);
    }

    @Override
    public void updateUser(User user) {
        User entity = userRepository.findById(user.getId());
        if (entity != null) {
            entity.setSsoId(user.getSsoId());
            if (!user.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            if (!CollectionUtils.isEmpty(user.getUserProfiles())) {
                entity.setUserProfiles(user.getUserProfiles());
            }
        }

        userRepository.update(entity);
    }

    @Override
    public void deleteUserBySSO(String sso) {
        User user = findBySSO(sso);
        Optional<Student> student = studentRepository.findByUser(user.getId());
        if (student.isPresent()) {
            studentRepository.delete(student.get().getId());
        }
        userRepository.deleteBySSO(sso);
    }

    @Override
    public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySSO(sso);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

}
