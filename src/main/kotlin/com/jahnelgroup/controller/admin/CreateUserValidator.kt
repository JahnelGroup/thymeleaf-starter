package com.jahnelgroup.controller.admin

import com.jahnelgroup.domain.user.User
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

class CreateUserValidator : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return User::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "", "Username must be provided.")
    }
}