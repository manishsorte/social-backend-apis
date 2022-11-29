package com.backend.social.socialbackendapis.controller;

import com.backend.social.socialbackendapis.payload.UserDto;
import com.backend.social.socialbackendapis.payload.UserPaginationResponse;
import com.backend.social.socialbackendapis.service.impl.UserServiceImpl;
import com.backend.social.socialbackendapis.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/create")
    private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    private ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto  ,@PathVariable("userId") Integer id){
        UserDto updatedUser = this.userService.updateUser(userDto,id);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<UserDto> getUserById(@PathVariable("userId")  Integer id){
        UserDto getUser = this.userService.getUserById(id);
        return new ResponseEntity<>(getUser,HttpStatus.FOUND);
    }

    @GetMapping("/allUsers")
    private ResponseEntity<UserPaginationResponse> getAllUsers(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                               @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        UserPaginationResponse allUsers = this.userService.getAllUsers(pageNumber, pageSize);
        return new ResponseEntity<>(allUsers, HttpStatus.FOUND);
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id){
        this.userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }
}
