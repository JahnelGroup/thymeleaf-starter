package com.jahnelgroup.controller.settings.user.account

import javax.validation.constraints.NotBlank

data class UpdatePasswordForm(
        @field:NotBlank(message = "Field required.")
        var password: String? = null,

        @field:NotBlank(message = "Field required.")
        var passwordConfrim: String? = null
)