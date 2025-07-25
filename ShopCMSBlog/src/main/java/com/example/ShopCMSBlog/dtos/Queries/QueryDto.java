package com.example.ShopCMSBlog.dtos.Queries;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QueryDto {
    int page;
    int size;
    String sortBy;
    String sortDir;
}
