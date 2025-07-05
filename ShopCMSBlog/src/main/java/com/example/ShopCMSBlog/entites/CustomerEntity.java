package com.example.ShopCMSBlog.entites;

import com.example.ShopCMSBlog.enums.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
    @Column(name = "fullname", nullable = false)
    String fullName;
    @Column(name = "phoneNumber", nullable = false)
    String phoneNumber;
    @Column(name = "address", nullable = false)
    String address;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    Gender gender;
    @Column(name ="date_of_birth")
    LocalDate dateOfBirth;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    UserEntity user;

}
