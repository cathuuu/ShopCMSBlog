package com.example.ShopCMSBlog.dtos;

import com.example.ShopCMSBlog.enums.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDto {
    Long id;
    String fullName;
    String phoneNumber;
    String address;
    Gender gender;
    LocalDate dateOfBirth;
    Long userId;
}
