package com.jahnelgroup.start.service.context

import com.jahnelgroup.start.domain.user.User

interface UserContextService {

    fun currentUser(): User
    fun currentUsername(): String
    fun currentAuthorities(): Set<String>

}