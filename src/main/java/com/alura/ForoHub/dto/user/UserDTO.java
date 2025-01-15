package com.alura.ForoHub.dto.user;

import java.util.List;

import com.alura.ForoHub.domain.model.User;
import com.alura.ForoHub.dto.profile.ProfileDTO;

public record UserDTO( Long userId,
                       String name,
                       String email,
                       List<ProfileDTO> profiles
) {
    public UserDTO(User user){
        this(user.getUserId(), user.getName(), user.getEmail(), user.getProfiles().stream().map(ProfileDTO::new).toList());
    }
}