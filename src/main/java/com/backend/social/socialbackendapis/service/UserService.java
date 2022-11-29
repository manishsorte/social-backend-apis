package com.backend.social.socialbackendapis.service;

import com.backend.social.socialbackendapis.payload.UserDto;
import com.backend.social.socialbackendapis.payload.UserPaginationResponse;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto getUserById(Integer userId);

    UserPaginationResponse getAllUsers(Integer pageNumber, Integer pageSize);

    void deleteUser(Integer userId);

}
