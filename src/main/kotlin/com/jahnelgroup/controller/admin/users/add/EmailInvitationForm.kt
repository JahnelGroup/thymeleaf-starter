package com.jahnelgroup.controller.admin.users.add

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class EmailInvitationForm(
    @field:NotBlank(message = "Field required.")
    @field:Email(message = "Email isn't valid.")
    var email: String? = null
)