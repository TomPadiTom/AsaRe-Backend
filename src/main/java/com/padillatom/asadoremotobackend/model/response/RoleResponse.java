package com.padillatom.asadoremotobackend.model.response;

import com.padillatom.asadoremotobackend.model.entity.RoleEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
public class RoleResponse {
    
    private String name;

    private String description;

    public static List<RoleResponse> toDto(Collection<RoleEntity> roles) {
        List<RoleResponse> response = new ArrayList<>();
        for (RoleEntity temp : roles) {
            response.add(RoleResponse.builder()
                    .name(temp.getName())
                    .description(temp.getDescription())
                    .build());
        }
        return response;
    }
}
