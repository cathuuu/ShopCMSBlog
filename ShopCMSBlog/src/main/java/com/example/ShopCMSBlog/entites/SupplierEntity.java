package com.example.ShopCMSBlog.entites;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name",unique = true, nullable = false)
    String name;
    @Column(name = "contact_name",unique = true, nullable = false)
    String contactName;
    @Column(name = "phone_number",unique = true, nullable = false)
    String phoneNumber;
    @Column(name = "email",unique = true, nullable = false)
    String email;
    @Column(name = "address",unique = true, nullable = false)
    String address;
}
