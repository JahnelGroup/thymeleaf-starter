package com.jahnelgroup.controller.settings.user.profile

import javax.validation.constraints.NotBlank

data class UpdatePreferencesForm(
        @field:NotBlank(message = "Field required.")
        var preferences: String? = null
)