package com.example.ShopCMSBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CommonRepository<E, ID> extends JpaRepository<E, ID> {
    <ID> void deleteByIdIn(List<ID> ids);
}
