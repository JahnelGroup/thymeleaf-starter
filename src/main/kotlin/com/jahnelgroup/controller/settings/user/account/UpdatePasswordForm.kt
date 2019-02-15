package com.jahnelgroup.controller.settings.user.account

import javax.validation.constraints.NotBlank

data class UpdatePasswordForm(
        @field:NotBlank(message = "Field required.")
        var password: String = "",

        @field:NotBlank(message = "Field required.")
        var passwordConfirm: String = ""
)