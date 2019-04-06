package com.jahnelgroup.controller.settings.user.profile

import javax.validation.constraints.NotBlank
import com.jahnelgroup.domain.preferences.*

data class UpdatePreferencesForm(
        @field:NotBlank(message = "Field required.")
        var preferences: Preferences? = null
)