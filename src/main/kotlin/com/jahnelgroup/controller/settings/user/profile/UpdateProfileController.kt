package com.jahnelgroup.controller.settings.user.profile

import com.jahnelgroup.domain.context.UserContextService
import com.jahnelgroup.domain.user.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class UpdateProfileController(
        private var userService: UserService,
        private var userContextService: UserContextService){

    @GetMapping("/settings")
    fun settings() = "redirect:/settings/profile"

    // Forward is the same as redirect but the URL remains the same
    @GetMapping("/settings/profile")
    fun profile() = "forward:/settings/${userContextService.currentUsername()}/profile"

    @GetMapping("/settings/{user}/profile")
    fun profile(model: Model, @PathVariable user: String): String{
        val u = userService.findByUsername(user)
        model.addAttribute("user", u)
        model.addAttribute("updateProfileForm",
                UpdateProfileForm(firstName = u.firstName, lastName = u.lastName, email = u.email))
        return "layouts/settings/user/profile"
    }

    @PostMapping("/settings/{user}/profile")
    fun updateProfile(model: Model, @PathVariable user: String,
            @Valid updateProfileForm: UpdateProfileForm, bindingResult: BindingResult): String{

        if( !bindingResult.hasErrors() ){
            userService.updateProfile(
                    user,
                    updateProfileForm.firstName!!,
                    updateProfileForm.lastName!!,
                    updateProfileForm.email!!)

            model.addAttribute("updateProfileSuccessMessage", "Success!")
        }

        model.addAttribute("user", userService.findByUsername(user))
        return "layouts/settings/user/profile"
    }
}