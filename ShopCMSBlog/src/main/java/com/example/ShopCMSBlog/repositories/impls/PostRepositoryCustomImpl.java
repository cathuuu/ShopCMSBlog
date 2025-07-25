package com.example.ShopCMSBlog.repositories.impls;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.dtos.Queries.ConditionDto;
import com.example.ShopCMSBlog.dtos.Queries.PostQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.ProductReviewQueryDto;
import com.example.ShopCMSBlog.repositories.PostRepositoryCustom;
import com.example.ShopCMSBlog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    @Autowired
    private BaseNativeQuery baseNativeQuery;
    @Override
    public Page<PostDto> getPost(PostQueryDto postQueryDto) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT bp.id, bp.title, bp.content , bp.image_url as imageUrl, bp.comment, bp.created_at as createdAt, bp.author_id as authorId FROM blog_posts bp WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM blog_posts bp WHERE 1=1");
        String orderBy = postQueryDto.getSortBy();
        String orderDir = postQueryDto.getSortDir();
        ConditionDto conditionData = makeCondition(postQueryDto);
        if (!StringUtils.isBlank(conditionData.getQueryCondition())) {
            sqlBuilder.append(conditionData.getQueryCondition());
            countSqlBuilder.append(conditionData.getQueryCondition());
        }
        if (!StringUtils.isBlank(orderBy)) {
            if(!StringUtils.isBlank(orderDir)) sqlBuilder.append(" ORDER BY " + orderBy +" "+orderDir);
        }
        Pageable pageable = PageRequest.of(postQueryDto.getPage(),  postQueryDto.getSize());
        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, PostDto.class,conditionData.getParams());
    }
    private ConditionDto makeCondition(PostQueryDto postQueryDto) {
        StringBuilder condition = new StringBuilder();
        Long postId = postQueryDto.getId();
        String postTitle = postQueryDto.getTitle();
        String postContent = postQueryDto.getContent();
        String postImageUrl = postQueryDto.getImageUrl();
        Long postAuthorId = postQueryDto.getAuthor();
        //       String createdAt = String.valueOf(productReviewQueryDto.getCreatedAt());
        Map<String, Object> params = new HashMap<>();
        if (postId != null) {
            condition.append(" AND bp.id LIKE :id");
            params.put("id", "%" + postId + "%");
        }
        if (postAuthorId != null) {
            condition.append(" AND bp.author_id LIKE :authorId");
            params.put("authorId", "%" + postAuthorId + "%");
        }
        if (!StringUtils.isBlank(postTitle)) {
            condition.append(" AND bp.title LIKE :title");
            params.put("title", "%" + postTitle + "%");
        }
        if (!StringUtils.isBlank(postContent)) {
            condition.append(" AND bp.content LIKE :content");
            params.put("content", "%" + postContent + "%");
        }
        if (!StringUtils.isBlank(postImageUrl)) {
            condition.append(" AND bp.imag_url LIKE :imageUrl");
            params.put("imageUrl", "%" + postImageUrl + "%");
        }
//        if (!StringUtils.isBlank(createdAt)) {
//            condition.append(" AND pr.created_at LIKE :createdAt");
//            params.put("createdAt", "%" + createdAt + "%");
//        }
        return ConditionDto.builder().params(params).queryCondition(condition.toString()).build();
    }

}
