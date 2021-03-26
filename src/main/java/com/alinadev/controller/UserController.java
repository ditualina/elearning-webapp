package com.alinadev.controller;

import com.alinadev.model.Course;
import com.alinadev.model.Student;
import com.alinadev.model.User;
import com.alinadev.model.UserProfile;
import com.alinadev.service.CourseService;
import com.alinadev.service.StudentService;
import com.alinadev.service.UserProfileService;
import com.alinadev.service.UserService;
import com.google.common.collect.Sets;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class UserController {

    @Resource
    UserService userService;

    @Resource
    StudentService studentService;

    @Resource
    CourseService courseService;

    @Resource
    MessageSource messageSource;

    @Resource
    UserProfileService userProfileService;

    @Resource
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/home-page", method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userHasAuthority("ROLE_USER", (UserDetails) principal)) {
            User user = userService.findBySSO(((UserDetails) principal).getUsername());
            Optional<Student> student = studentService.findByUser(user.getId());
            if (student.isPresent()) {
                model.put("student", student.get());
            }
            List<Course> next3Upcoming = courseService.findAllNext3Days();
            List<Course> overDues = courseService.retrieveOverdue();

            if (!CollectionUtils.isEmpty(overDues)) {
                model.put("overdues", overDues);
            }

            if (!CollectionUtils.isEmpty(next3Upcoming)) {
                model.put("nextCourses", next3Upcoming);
            }
            return "home-page";
        } else {
            return "redirect:/list";
        }
    }

    @RequestMapping(value = {"/newstudent"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();

        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "student-registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/newstudent"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, HttpServletRequest request,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "student-registration";
        }

        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
            FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "student-registration";
        }

        Integer scholarYear = Integer.parseInt(request.getParameter("scholarYear"));

        UserProfile profile = userProfileService.findByType("USER");
        user.setUserProfiles(Sets.newHashSet(profile));

        userService.saveUser(user);

        studentService.createStudent(user, scholarYear);

        Optional<Student> student = studentService.findByUser(user.getId());

        model.addAttribute("student", student.get());
        //return "success";
        return "home-page";
    }

    public static boolean userHasAuthority(String authority, UserDetails userDetails) {
        for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }

        return false;
    }

}
