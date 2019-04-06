package com.jahnelgroup.controller.admin.users.add

import com.jahnelgroup.domain.user.User
import javax.validation.constraints.NotNull
import javax.validation.constraints.NotBlank
import com.jahnelgroup.domain.preferences.*

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
        var passwordConfirm: String = ""//,

        //var preferences: Preferences? = null
){
    fun toUser() = User(
        username = this.username,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password//,
        //preferences = this.preferences
    )
}