package com.example.ShopCMSBlog.repositories.impls;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.CommentDto;
import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.Queries.CommentQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.ConditionDto;
import com.example.ShopCMSBlog.dtos.Queries.PostQueryDto;
import com.example.ShopCMSBlog.repositories.CommentRepositoryCustom;
import com.example.ShopCMSBlog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    @Autowired
    private BaseNativeQuery baseNativeQuery;
    @Override
    public Page<CommentDto> getComment(CommentQueryDto commentQueryDto) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT cm.id, cm.customer_id, cm.post_id as postId , cm.user_id as userId, cm.content, cm.created_at as createdAt FROM blog_comments cm WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM blog_comments cm WHERE 1=1");
        String orderBy = commentQueryDto.getSortBy();
        String orderDir = commentQueryDto.getSortDir();
        ConditionDto conditionData = makeCondition(commentQueryDto);
        if (!StringUtils.isBlank(conditionData.getQueryCondition())) {
            sqlBuilder.append(conditionData.getQueryCondition());
            countSqlBuilder.append(conditionData.getQueryCondition());
        }
        if (!StringUtils.isBlank(orderBy)) {
            if(!StringUtils.isBlank(orderDir)) sqlBuilder.append(" ORDER BY " + orderBy +" "+orderDir);
        }
        Pageable pageable = PageRequest.of(commentQueryDto.getPage(),  commentQueryDto.getSize());
        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, CommentDto.class,conditionData.getParams());
    }
    private ConditionDto makeCondition(CommentQueryDto commentQueryDto) {
        StringBuilder condition = new StringBuilder();
        Long commentId = commentQueryDto.getId();
        Long commentPostId = commentQueryDto.getPostId();
        Long commentUserId = commentQueryDto.getUserId();
        String commentContent = commentQueryDto.getContent();

        //       String createdAt = String.valueOf(productReviewQueryDto.getCreatedAt());
        Map<String, Object> params = new HashMap<>();
        if (commentId != null) {
            condition.append(" AND cm.id LIKE :id");
            params.put("id", "%" + commentId + "%");
        }
        if (commentPostId != null) {
            condition.append(" AND cm.post_id LIKE :postId");
            params.put("postId", "%" + commentPostId + "%");
        }
        if (commentUserId != null) {
            condition.append(" AND cm.user_id LIKE :userId");
            params.put("userId", "%" + commentUserId + "%");
        }
        if (!StringUtils.isBlank(commentContent)) {
            condition.append(" AND cm.content LIKE :content");
            params.put("content", "%" + commentContent + "%");
        }
        return ConditionDto.builder().params(params).queryCondition(condition.toString()).build();
    }
}
