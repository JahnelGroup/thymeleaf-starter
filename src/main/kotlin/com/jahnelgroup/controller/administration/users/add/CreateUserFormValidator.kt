package com.jahnelgroup.controller.administration.users.add

import org.passay.*
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator
import java.util.*


class CreateUserFormValidator : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return CreateUserForm::class.java == clazz
    }

    override fun validate(createUserForm: Any, errors: Errors) {
        if(createUserForm is CreateUserForm){
            arrayOf("username", "firstName", "lastName", "email", "password", "passwordConfrim").forEach {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, it, "", "Field required.")
            }

            // Both passwords were provided
            if( !errors.hasFieldErrors("password") && !errors.hasFieldErrors("passwordConfrim") ){

                // passwords don't match
                if( !createUserForm.password.equals(createUserForm.passwordConfrim) ){
                    errors.rejectValue("password", "", "Passwords do not match.")
                    errors.rejectValue("passwordConfrim", "", "Passwords do not match.")
                }

                // check complexity
                else{
                    // Validate password
                    val passwordValidator = PasswordValidator(Arrays.asList(
                            LengthRule(8, 30),
                            RepeatCharacterRegexRule(RepeatCharacterRegexRule.MINIMUM_SEQUENCE_LENGTH),
                            WhitespaceRule()))

                    if( !passwordValidator.validate(PasswordData(createUserForm.password)).isValid ){
                        errors.rejectValue("password", "", "Password does not meet the complexity requirements.")
                    }
                }
            }
        }
    }
}