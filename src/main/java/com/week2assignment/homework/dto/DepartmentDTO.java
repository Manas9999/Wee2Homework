package com.week2assignment.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

 private long id;
 @NotEmpty(message = "Tittle of the department cannot be blank or empty")
 //
 // @NotBlank(message = "Tittle of the department cannot be blank or empty")
 private String tittle;
 @AssertTrue(message = "Employee should be active")
 @JsonProperty("isActive")
 private Boolean isactive;
 @PastOrPresent(message = "DateOfJoining field in Employee cannot be in the future")
 private LocalDate createdAt;
}
