package com.backend.social.socialbackendapis.service.impl;

import com.backend.social.socialbackendapis.entity.User;
import com.backend.social.socialbackendapis.exception.ResourceNotFoundException;
import com.backend.social.socialbackendapis.payload.UserDto;
import com.backend.social.socialbackendapis.payload.UserPaginationResponse;
import com.backend.social.socialbackendapis.repository.UserRepository;
import com.backend.social.socialbackendapis.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        User createdUser = this.userRepository.save(user);
        return this.userToDto(createdUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User retrievedUserById = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        retrievedUserById.setName(userDto.getName());
        retrievedUserById.setAbout(userDto.getAbout());
        retrievedUserById.setPassword(userDto.getPassword());
        retrievedUserById.setEmail(userDto.getEmail());

        User updatedUser = this.userRepository.save(retrievedUserById);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User retrievedUserById = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        return this.userToDto(retrievedUserById);
    }

    @Override
    public UserPaginationResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageInfo = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> userPage = this.userRepository.findAll(pageInfo);
        List<User> listOfAllUsers = userPage.getContent();

        List<UserDto> userDtoList = listOfAllUsers.stream()
                .map(this::userToDto)
                .collect(Collectors.toList());

        UserPaginationResponse userPaginationResponse = new UserPaginationResponse();
        userPaginationResponse.setContent(userDtoList);
        userPaginationResponse.setPageNumber(userPage.getNumber());
        userPaginationResponse.setPageSize(userPage.getSize());
        userPaginationResponse.setTotalElements(userPage.getTotalElements());
        userPaginationResponse.setTotalPages(userPage.getTotalPages());
        userPaginationResponse.setLastPage(userPage.isLast());

        return userPaginationResponse;
    }

    @Override
    public void deleteUser(Integer userId) {
        this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        this.userRepository.deleteById(userId);
    }

    private User dtoToUser(UserDto userDto) {
        return this.modelMapper.map(userDto,User.class);
    }

    private UserDto userToDto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }

}
