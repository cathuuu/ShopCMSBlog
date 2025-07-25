package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.dtos.Queries.ProductReviewQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.SupplierQueryDto;
import com.example.ShopCMSBlog.entites.ProductEntity;
import com.example.ShopCMSBlog.entites.ProductReviewEntity;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.mappers.ProductMapper;
import com.example.ShopCMSBlog.mappers.ProductReviewMapper;
import com.example.ShopCMSBlog.repositories.ProductReviewRepository;
import com.example.ShopCMSBlog.services.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductReviewServiceImpl extends CommonServiceImpl<ProductReviewEntity, Long, ProductReviewRepository> implements ProductReviewService {
    private final ProductReviewMapper productReviewMapper;
    @Autowired
    private BaseNativeQuery baseNativeQuery;
    public ProductReviewServiceImpl(ProductReviewRepository repo, ProductReviewMapper productReviewMapper) {
        super(repo);
        this.productReviewMapper = productReviewMapper;

    }

    @Override
    public List<ProductReviewDto> findByIdAndComment(Long id, String comment) {
        return repo.findByIdAndComment(id, comment);
    }

    @Override
    public ProductReviewDto save(ProductReviewDto dto) {
        validateWhenCreateOrUpdate(dto);
        ProductReviewEntity productReviewEntity = productReviewMapper.toEntity(dto);
        productReviewEntity = repo.save(productReviewEntity);
        return productReviewMapper.toDto(productReviewEntity);
    }

    @Override
    public void delete(Long id) {
        ProductReviewEntity productReviewEntity = repo.findById(id).orElseThrow(() ->
                new RuntimeException("Cann't found this Supplier " + id));
        repo.delete(productReviewEntity);
    }

    @Override
    public Page<ProductReviewDto> getProductReview(ProductReviewQueryDto productReviewQueryDto) {
        return repo.getProductReiew(productReviewQueryDto);
    }


    private void validateWhenCreateOrUpdate(ProductReviewDto dto) {
        String roleName = dto.getComment();
        if (roleName == null || roleName.trim().isEmpty()) {
            throw new RuntimeException("Customer name is required");
        }
    }
}
