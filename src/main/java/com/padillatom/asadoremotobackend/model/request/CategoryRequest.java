package com.padillatom.asadoremotobackend.model.request;

import com.padillatom.asadoremotobackend.model.entity.CategoryEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String description;

    @Size(min = 3, max = 30)
    private String image;

    public static CategoryEntity toEntity(CategoryRequest request) {
        return CategoryEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImage())
                .build();
    }
}
