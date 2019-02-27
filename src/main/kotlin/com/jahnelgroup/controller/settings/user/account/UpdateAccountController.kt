package com.jahnelgroup.controller.settings.user.account

import com.jahnelgroup.domain.context.UserContextService
import com.jahnelgroup.domain.user.UserService
import com.jahnelgroup.validator.PasswordComplexityValidator
import org.springframework.data.repository.query.Param
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Controller
class UpdateAccountController(
        private var userService: UserService,
        private var userContextService: UserContextService){

    @ModelAttribute("updatePasswordForm")
    fun updatePasswordForm() = UpdatePasswordForm()

    // Forward is the same as redirect but the URL remains the same
    @GetMapping("/settings/account")
    fun profile() = "forward:/settings/${userContextService.currentUsername()}/account"

    @PreAuthorize("hasRole('ROLE_ADMIN') || #user == authentication.name")
    @GetMapping("/settings/{user}/account")
    fun profile(model: Model, @Param("user") @PathVariable user: String): String{
        model.addAttribute("user", userService.findByUsername(user))
        return "layouts/settings/user/account"
    }

    /**
     * This is intended to be called asynchronously with ajax.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') || #user == authentication.name")
    @PostMapping("/settings/{user}/updatePassword")
    fun updatePassword(model: Model, @PathVariable user: String,
            @Valid updatePasswordForm: UpdatePasswordForm, bindingResult: BindingResult, response: HttpServletResponse): String{

        if(!bindingResult.hasErrors()){
            PasswordComplexityValidator().validate(updatePasswordForm, bindingResult)
            if( !bindingResult.hasErrors() ){
                userService.updatePassword(user, updatePasswordForm.password)
            }
        }

        if( bindingResult.hasErrors() ){
            response.setHeader("hasErrors", "true")
        }

        model.addAttribute("user", userService.findByUsername(user))
        return "fragments/account :: updatePasswordModal"
    }

}