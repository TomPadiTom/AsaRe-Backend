package com.padillatom.asadoremotobackend.model.response;

import com.padillatom.asadoremotobackend.model.entity.CategoryEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CategoryResponse {

    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    public static List<CategoryResponse> toDtoList(List<CategoryEntity> list) {
        return list.stream()
                .map(cat -> CategoryResponse.builder()
                        .id(cat.getId())
                        .title(cat.getTitle())
                        .description(cat.getDescription())
                        .imageUrl(cat.getImageUrl())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
