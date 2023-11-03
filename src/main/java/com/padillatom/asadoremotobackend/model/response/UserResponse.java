package com.padillatom.asadoremotobackend.model.response;

import com.padillatom.asadoremotobackend.model.entity.BillingAddressEntity;
import com.padillatom.asadoremotobackend.model.entity.ShippingAddressEntity;
import com.padillatom.asadoremotobackend.model.entity.UserEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private AccountDetailsResponse accountDetails;

    private BillingAddressEntity billingAddress;

    private ShippingAddressEntity shippingAddress;

    private List<RoleResponse> roles;

    public static UserResponse toDto(UserEntity entity) {
        if (entity == null) return null;

        return UserResponse.builder()
                .id(entity.getId())
                .accountDetails(AccountDetailsResponse.toDto(entity.getAccountDetails()))
                .roles(RoleResponse.toDto(entity.getRoles()))
                .billingAddress(entity.getBillingAddress())
                .shippingAddress(entity.getShippingAddress())
                .build();
    }
}
