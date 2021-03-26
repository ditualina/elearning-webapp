package com.alinadev.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.alinadev.model.Course;
import com.alinadev.model.User;
import com.alinadev.service.CourseService;
import com.alinadev.service.StudentService;
import com.alinadev.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alinadev.model.Student;
import com.alinadev.repository.StudentRepository;

@Controller
public class StudentController {

    @Resource
    StudentService studentService;

    @Resource
    UserService userService;

    @Resource
    CourseService courseService;

    public List<Student> studentList;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView displayStudent(HttpServletRequest request) {
        studentList = new ArrayList<>();

        studentList = studentService.retrieveAll();

        return new ModelAndView("student", "students", studentList);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String getCourses(ModelMap model) {
        List<Course> courses = courseService.retrieveaAll();

        model.addAttribute("courses", courses);

        return "courses";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String getProjectsPage(ModelMap model) {
        List<Course> courses = courseService.retrieveaAll();

        model.addAttribute("courses", courses);
        return "projects";
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String getProjectsForCourse(@RequestParam(value = "course", required = false) int courseId,
                                       ModelMap model) {
        Optional<Course> course = courseService.retrieveById(courseId);
        List<Course> courses = courseService.retrieveaAll();
        model.addAttribute("courses", courses);

        if (course.isPresent()) {
            model.addAttribute("courseName", course.get().getName());
            return "upload-file";
        }
        return "courses";
    }

    @RequestMapping(value = "/user-profile", method = RequestMethod.GET)
    public String getStudentProfile(Model map) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findBySSO(((UserDetails) principal).getUsername());
        Optional<Student> student = studentService.findByUser(user.getId());
        if (student.isPresent()) {
            map.addAttribute("student", student.get());
        }
        return "user-profile";
    }

    @RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
    public String editStudentProfile(Model map) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findBySSO(((UserDetails) principal).getUsername());
        Optional<Student> student = studentService.findByUser(user.getId());
        if (student.isPresent()) {
            map.addAttribute("student", student.get());
        }
        return "edit-profile";
    }

    @RequestMapping(value = "/edit-profile", method = RequestMethod.POST)
    public String updateStudentProfile(@Valid Student student, BindingResult result,
                                       ModelMap model) {
        Student updatedStudent = studentService.updateStudent(student);
        model.addAttribute("student", updatedStudent);
        return "user-profile";
    }

}