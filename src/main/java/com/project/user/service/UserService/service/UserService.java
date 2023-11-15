package com.project.user.service.UserService.service;

import com.project.user.service.UserService.entity.UserEntity;
import com.project.user.service.UserService.model.User;
import com.project.user.service.UserService.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(User user) {
        // Save the user to the database
        try {
            UserEntity userEntity = UserEntity.builder()
                    .id(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                    .name(user.getName())
                    .age(user.getAge())
                    .address(user.getAddress())
                    .email(user.getEmail())
                    .contactNumber(user.getContactNumber())
                    .build();
            logger.info("Saving this user in DB : {}", userEntity);
            return userRepository.save(userEntity);
        }catch (Exception ex){
            logger.error("Error in saving user to Db : {} ", ex.getLocalizedMessage());
            return null;
        }
    }
}
