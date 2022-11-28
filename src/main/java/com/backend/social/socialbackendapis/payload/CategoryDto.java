package com.backend.social.socialbackendapis.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {


    private int category_Id;

    @NotEmpty(message = "Please fill title")
    @Size(min = 3, max = 10)
    private String title;

    @NotEmpty(message = "Please fill description")
    @Size(min = 10, max = 100)
    private String description;
}
