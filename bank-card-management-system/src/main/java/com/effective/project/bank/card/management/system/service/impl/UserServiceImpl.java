package com.effective.project.bank.card.management.system.service.impl;

import com.effective.project.bank.card.management.system.dto.mapper.UserMapper;
import com.effective.project.bank.card.management.system.dto.request.UserUpdateRequest;
import com.effective.project.bank.card.management.system.dto.response.UserUpdateResponse;
import com.effective.project.bank.card.management.system.entity.User;
import com.effective.project.bank.card.management.system.repository.UserRepository;
import com.effective.project.bank.card.management.system.service.UserService;
import com.effective.project.bank.card.management.system.utils.FieldValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserUpdateResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        if (FieldValidator.isNotEmpty(userUpdateRequest.getEmail())) {
            user.setEmail(userUpdateRequest.getEmail());
        }

        if (FieldValidator.isNotEmpty(userUpdateRequest.getPassword())) {
            user.setPassword(userUpdateRequest.getPassword());
        }

        if (FieldValidator.isNotEmpty(userUpdateRequest.getFullName())) {
            user.setFullName(userUpdateRequest.getFullName());
        }

        if (userUpdateRequest.getRoles() != null && !userUpdateRequest.getRoles().isEmpty()) {
            user.setRoles(userUpdateRequest.getRoles());
        }

        if (userUpdateRequest.getCards() != null && !userUpdateRequest.getCards().isEmpty()) {
            user.setCards(userUpdateRequest.getCards());
        }

        User updatedUser = userRepository.save(user);

        return UserMapper.mapEntityToUserUpdateResponse(updatedUser);
    }

    @Override
    @Transactional
    public Long deleteUser(Long userId) {

        userRepository.deleteById(userId);

        return userId;
    }

}
