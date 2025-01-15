package com.alura.ForoHub.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.ForoHub.domain.model.Profile;
import com.alura.ForoHub.domain.model.User;
import com.alura.ForoHub.domain.repository.ProfileRepository;
import com.alura.ForoHub.domain.repository.UserRepository;
import com.alura.ForoHub.dto.profile.ProfileDTO;
import com.alura.ForoHub.dto.user.RegisterUserDTO;
import com.alura.ForoHub.dto.user.UpdateUserDTO;
import com.alura.ForoHub.dto.user.UserDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired 
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid RegisterUserDTO data, UriComponentsBuilder uriBuilder){
        Profile profile = new Profile(data);

        User user = new User();
        user.setName(data.name());
        user.setPassword(passwordEncoder.encode(data.password()));
        user.setEmail(data.email());

        User userSaved = repository.save(user);
        userSaved.addProfile(profileRepository.save(profile));
        repository.save(userSaved);

        URI url = uriBuilder.path("/users/{userId}").buildAndExpand(userSaved.getUserId()).toUri();
        List<Profile> userProfiles = repository.findProfilesByUserId(userSaved.getUserId());
        List<ProfileDTO> profilesDTO = userProfiles.stream()
                .map(p -> new ProfileDTO(p))
                .collect(Collectors.toList());
        var userDTO = new UserDTO(userSaved.getUserId(), userSaved.getName(), userSaved.getEmail(), profilesDTO);

        return ResponseEntity.created(url).body(userDTO);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable){
        return ResponseEntity.ok(repository.findAll(pageable).map(UserDTO::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UpdateUserDTO data){
        User user = repository.getReferenceById(data.userId());
        user.setName(data.name());
        user.setPassword(data.password());
        user.setEmail(data.email());
        User userSaved = repository.save(user);
        var userDto = new UserDTO(userSaved);

        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping("/{userId}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        repository.deleteById(userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
        User user = repository.getReferenceById(userId);

        return ResponseEntity.ok().body(new UserDTO(user));
    }
}