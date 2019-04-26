package com.jahnelgroup.controller.admin.users.add

import com.jahnelgroup.domain.user.User
import javax.validation.constraints.NotNull

data class CreateUserForm (
        @field:NotNull
        var username: String = "",

        @field:NotNull
        var firstName: String = "",

        @field:NotNull
        var lastName: String = "",

        @field:NotNull
        var email: String = "",

        @field:NotNull
        var password: String = "",

        @field:NotNull
        var passwordConfirm: String = ""
){
    fun toUser() = User(
        username = this.username,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password
    )
}