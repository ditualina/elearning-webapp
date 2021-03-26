package com.alinadev.adapter;

import com.alinadev.dto.UserDto;
import com.alinadev.model.Student;

/**
 * Created by: Alina Ditu
 * Date: 5/21/17
 */
public class UserAdapter {

    public static Student fromUserDto(UserDto userDto)  {
        Student student = new Student();
        student.setFirstName(userDto.getFirstName());
        student.setLastName(userDto.getLastName());
        student.setEmail(userDto.getEmail());

        return student;
    }
}
