package com.jahnelgroup.validator

import com.jahnelgroup.controller.admin.users.add.CreateUserForm
import com.jahnelgroup.controller.settings.user.account.UpdatePasswordForm
import org.passay.*
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator
import java.util.*

class PasswordComplexityValidator : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return CreateUserForm::class.java == clazz || UpdatePasswordForm::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        if( target is CreateUserForm ){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Field required.")
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "", "Field required.")
            doValidate(target.password!!, target.passwordConfirm!!, errors)
        }else if( target is UpdatePasswordForm ){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Field required.")
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "", "Field required.")
            doValidate(target.password!!, target.passwordConfirm!!, errors)
        }
    }

    private fun doValidate(password: String, passwordConfirm: String, errors: Errors){
        // passwords don't match
        if( password != passwordConfirm ){
            errors.rejectValue("password", "", "Passwords do not match.")
            errors.rejectValue("passwordConfirm", "", "Passwords do not match.")
        }

        // check complexity
        else{
            // Validate password
            val passwordValidator = PasswordValidator(Arrays.asList(
                    LengthRule(8, 30),
                    RepeatCharacterRegexRule(RepeatCharacterRegexRule.MINIMUM_SEQUENCE_LENGTH),
                    WhitespaceRule()))

            if( !passwordValidator.validate(PasswordData(password)).isValid ){
                errors.rejectValue("password", "", "Password does not meet the complexity requirements.")
            }
        }
    }




}