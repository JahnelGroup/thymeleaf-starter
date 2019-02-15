package com.jahnelgroup.service

import com.jahnelgroup.config.loggerFor
import com.jahnelgroup.domain.user.User
import com.jahnelgroup.domain.user.UserAuthorityRepo
import com.jahnelgroup.domain.user.UserRepo
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
        private var userRepo: UserRepo,
        private var userAuthorityRepo: UserAuthorityRepo,
        private var passwordEncoder: PasswordEncoder) {

    val logger = loggerFor(UserService::class.java)

    // TODO: only Admin should be able to call this
    fun createUser(user: User){
        user.password = passwordEncoder.encode(user.password)
        user.addAuthority("ROLE_USER")

        logger.info("createUser: {}", user)

        userRepo.save(user)
    }

    fun updatePassword(user: User){
        user.password = passwordEncoder.encode(user.password)
        logger.info("updatePassword: {}", user)
        userRepo.save(user)
    }

}