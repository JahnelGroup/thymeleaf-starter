package com.jahnelgroup.service.context

import com.jahnelgroup.domain.user.User

interface UserContextService {

    fun currentUser(): User
    fun currentUsername(): String
    fun currentAuthorities(): Set<String>

}