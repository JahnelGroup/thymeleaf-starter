package com.jahnelgroup.service.context

import com.jahnelgroup.domain.user.User
import com.jahnelgroup.domain.user.UserRepo
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserContextServiceImpl(private val userRepo: UserRepo) : UserContextService {

    override fun currentUsername(): String {
        val username = SecurityContextHolder.getContext().authentication.name
        return username ?: throw RuntimeException("UnauthenticatedException")
    }

    override fun currentUser(): User =
            userRepo.findByUsername(currentUsername()).get()

    override fun currentAuthorities(): Set<String> {
        var user = SecurityContextHolder.getContext().authentication.principal as
                org.springframework.security.core.userdetails.User
        return user.authorities.map{ it.authority }.toSet()
    }

}