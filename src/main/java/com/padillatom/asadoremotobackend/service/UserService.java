package com.padillatom.asadoremotobackend.service;

import com.padillatom.asadoremotobackend.model.request.UserDetailsUpdateRequest;
import com.padillatom.asadoremotobackend.model.response.UserResponse;

public interface UserService {

    UserResponse findByUserId(Long id);

    UserResponse meData(String email);

    UserResponse userUpdate(String email, UserDetailsUpdateRequest request);
}
