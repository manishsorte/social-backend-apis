package com.backend.social.socialbackendapis.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {

    private static final String regex = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";

    private int id;

    @NotEmpty(message = "Name cannot be less than 3 characters and more than 20")
    @Size(min = 3, max = 20)
    private String name;

    @Email(message = "Email is invalid")
    private String email;

    @NotEmpty(message = "Invalid password!")
    @Pattern(regexp = regex)
    private String password;

    @NotEmpty(message = "About cannot be less than 4 character")
    @Size(min = 4)
    private String about;

}
