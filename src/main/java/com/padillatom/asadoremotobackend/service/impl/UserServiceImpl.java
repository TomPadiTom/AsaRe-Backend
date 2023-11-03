package com.padillatom.asadoremotobackend.service.impl;

import com.padillatom.asadoremotobackend.service.UserService;
import com.padillatom.asadoremotobackend.model.entity.UserEntity;
import com.padillatom.asadoremotobackend.model.request.UserDetailsUpdateRequest;
import com.padillatom.asadoremotobackend.model.response.UserResponse;
import com.padillatom.asadoremotobackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse findByUserId(Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow();
        return UserResponse.toDto(foundUser);
    }

    @Override
    public UserResponse meData(String email) {
        var foundUser = userRepository.findByAccountDetailsEmail(email).orElseThrow();
        return UserResponse.toDto(foundUser);
    }

    @Override
    public UserResponse userUpdate(String email, UserDetailsUpdateRequest request) {

        var foundUser = userRepository.findByAccountDetailsEmail(email).orElseThrow();

        // Set Account Details
        foundUser.getAccountDetails().setFirstName(request.getFirstName());
        foundUser.getAccountDetails().setLastName(request.getLastName());
        foundUser.getAccountDetails().setDisplayName(request.getDisplayName());
        foundUser.getAccountDetails().setPhone(request.getPhone());
        foundUser.getAccountDetails().setBirthDate(request.getBirthDate());
        // Set Addresses
        foundUser.setBillingAddress(UserDetailsUpdateRequest.toBillingAddress(foundUser.getBillingAddress(), request));
        foundUser.setShippingAddress(UserDetailsUpdateRequest.toShippingAddress(foundUser.getShippingAddress(), request));

        return UserResponse.toDto(userRepository.save(foundUser));
    }
}
