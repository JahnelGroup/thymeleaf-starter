package com.jahnelgroup.controller.settings.user.account

import com.jahnelgroup.domain.user.UserRepo
import com.jahnelgroup.service.UserService
import com.jahnelgroup.service.context.UserContextService
import com.jahnelgroup.validator.PasswordComplexityValidator
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Controller
class UpdateAccountController(
        private var userRepo: UserRepo,
        private var userService: UserService,
        private var userContextService: UserContextService){

    @ModelAttribute("updatePasswordForm")
    fun updatePasswordForm() = UpdatePasswordForm()

    // Forward is the same as redirect but the URL remains the same
    @GetMapping("/settings/account")
    fun profile() = "forward:/settings/${userContextService.currentUsername()}/account"

    @GetMapping("/settings/{user}/account")
    fun profile(model: Model, @PathVariable user: String): String{
        // TODO: user may be null
        model.addAttribute("user", userRepo.findByUsername(user).get())
        return "layouts/settings/user/account"
    }

    /**
     * This is intended to be called asynchronously with ajax.
     */
    @PostMapping("/settings/{user}/updatePassword")
    fun updatePassword(model: Model, @PathVariable user: String,
            @Valid updatePasswordForm: UpdatePasswordForm, bindingResult: BindingResult, response: HttpServletResponse): String{

        if(!bindingResult.hasErrors()){
            PasswordComplexityValidator().validate(updatePasswordForm, bindingResult)
            if( !bindingResult.hasErrors() ){
                // TODO: user may be null
                var u = userRepo.findByUsername(user).get()
                u.password = updatePasswordForm.password
                userService.updatePassword(u)
            }
        }

        if( bindingResult.hasErrors() ){
            response.setHeader("hasErrors", "true")
        }

        var u = userRepo.findByUsername(user).get()
        model.addAttribute("user", u)

        return "fragments/modals/updatePassword :: updatePasswordForm"
    }

}