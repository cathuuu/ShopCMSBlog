package com.example.ShopCMSBlog.entites;

import com.example.ShopCMSBlog.enums.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "full_name", nullable = false)
    String fullName;
    @Column(name = "phone_number", nullable = false)
    String phoneNumber;
    @Column(name = "address", nullable = false)
    String address;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    Gender gender;
    @Column(name ="date_of_birth")
    LocalDate dateOfBirth;
    @Column(name = "user_id")
    Long userId;

}
