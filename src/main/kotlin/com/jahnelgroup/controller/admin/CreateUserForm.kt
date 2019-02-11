package com.jahnelgroup.controller.admin

import com.jahnelgroup.domain.user.User

data class CreateUserForm (
        var username: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var email: String? = null,
        var password: String? = null,
        var passwordConfrim: String? = null
){
    fun toUser() = User(
            username = this.username!!,
            firstName = this.firstName!!,
            lastName = this.lastName!!,
            email = this.email!!,
            password = this.password!!
    )
}