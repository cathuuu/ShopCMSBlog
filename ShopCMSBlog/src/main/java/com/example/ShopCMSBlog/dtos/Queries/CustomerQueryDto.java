package com.example.ShopCMSBlog.dtos.Queries;

import com.example.ShopCMSBlog.enums.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerQueryDto extends QueryDto{
    Long id;
    String fullName;
    String phoneNumber;
    String address;
    Gender gender;
    LocalDate dateOfBirth;
    Long userId;
}
