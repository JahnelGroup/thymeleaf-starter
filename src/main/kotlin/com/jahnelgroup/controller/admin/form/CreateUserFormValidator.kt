package com.jahnelgroup.controller.admin.form

import com.jahnelgroup.domain.user.User
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

class CreateUserFormValidator : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return User::class.java == clazz
    }

    override fun validate(createUserForm: Any, errors: Errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Username must be provided.")
    }
}