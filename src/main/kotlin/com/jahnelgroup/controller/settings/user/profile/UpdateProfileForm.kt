package com.jahnelgroup.controller.settings.user.profile

import javax.validation.constraints.NotBlank

data class UpdateProfileForm(
        @field:NotBlank(message = "Field required.")
        var firstName: String? = null,

        @field:NotBlank(message = "Field required.")
        var lastName: String? = null
)