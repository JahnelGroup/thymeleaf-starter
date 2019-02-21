package com.jahnelgroup.domain.context

import com.jahnelgroup.domain.user.User
import org.springframework.security.core.userdetails.UserDetails

interface UserContextService {

    fun currentUser(): User?
    fun currentUsername(): String?
    fun currentAuthorities(): Set<String>?
    fun currentUserDetails(): UserDetails?

}