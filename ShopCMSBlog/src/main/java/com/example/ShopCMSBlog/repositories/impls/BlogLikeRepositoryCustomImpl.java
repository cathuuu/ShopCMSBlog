package com.example.ShopCMSBlog.repositories.impls;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.BlogLikeDto;
import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.Queries.BlogLikeQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.ConditionDto;
import com.example.ShopCMSBlog.dtos.Queries.PostQueryDto;
import com.example.ShopCMSBlog.repositories.BlogLikeRepositoryCustom;
import com.example.ShopCMSBlog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BlogLikeRepositoryCustomImpl implements BlogLikeRepositoryCustom {
    @Autowired
    private BaseNativeQuery baseNativeQuery;
    @Override
    public Page<BlogLikeDto> getBlogLike(BlogLikeQueryDto blogLikeQueryDto) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT bl.id, bl.post_id as postId, bl.user_id as userId, bl.created_at as createdAt FROM blog_likes bl WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM blog_likes bl WHERE 1=1");
        String orderBy = blogLikeQueryDto.getSortBy();
        String orderDir = blogLikeQueryDto.getSortDir();
        ConditionDto conditionData = makeCondition(blogLikeQueryDto);
        if (!StringUtils.isBlank(conditionData.getQueryCondition())) {
            sqlBuilder.append(conditionData.getQueryCondition());
            countSqlBuilder.append(conditionData.getQueryCondition());
        }
        if (!StringUtils.isBlank(orderBy)) {
            if(!StringUtils.isBlank(orderDir)) sqlBuilder.append(" ORDER BY " + orderBy +" "+orderDir);
        }
        Pageable pageable = PageRequest.of(blogLikeQueryDto.getPage(),  blogLikeQueryDto.getSize());
        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, BlogLikeDto.class,conditionData.getParams());
    }
    private ConditionDto makeCondition(BlogLikeQueryDto blogLikeQueryDto) {
        StringBuilder condition = new StringBuilder();
        Long blogLikeId = blogLikeQueryDto.getId();
        Long blogLikePostId = blogLikeQueryDto.getPostId();
        Long blogLikeUserId = blogLikeQueryDto.getUserId();
        //       String createdAt = String.valueOf(productReviewQueryDto.getCreatedAt());
        Map<String, Object> params = new HashMap<>();
        if (blogLikeId != null) {
            condition.append(" AND bl.id LIKE :id");
            params.put("id", "%" + blogLikeId + "%");
        }
        if (blogLikePostId != null) {
            condition.append(" AND bl.post_id LIKE :postId");
            params.put("postId", "%" + blogLikePostId + "%");
        }
        if (blogLikeUserId != null) {
            condition.append(" AND bp.user_id LIKE :userId");
            params.put("userId", "%" + blogLikePostId + "%");
        }
//        if (!StringUtils.isBlank(createdAt)) {
//            condition.append(" AND pr.created_at LIKE :createdAt");
//            params.put("createdAt", "%" + createdAt + "%");
//        }
        return ConditionDto.builder().params(params).queryCondition(condition.toString()).build();
    }
}
