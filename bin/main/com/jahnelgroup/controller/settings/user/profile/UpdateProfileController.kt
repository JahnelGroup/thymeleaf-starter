package com.jahnelgroup.controller.settings.user.profile

import com.jahnelgroup.domain.user.UserRepo
import com.jahnelgroup.domain.context.UserContextService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class UpdateProfileController(
        private var userRepo: UserRepo,
        private var userContextService: UserContextService){

    @GetMapping("/settings")
    fun settings() = "redirect:/settings/profile"

    // Forward is the same as redirect but the URL remains the same
    @GetMapping("/settings/profile")
    fun profile() = "forward:/settings/${userContextService.currentUsername()}/profile"

    @GetMapping("/settings/{user}/profile")
    fun profile(model: Model, @PathVariable user: String): String{
        // TODO: user may be null
        val u = userRepo.findByUsername(user).get()
        model.addAttribute("user", u)
        model.addAttribute("updateProfileForm",
                UpdateProfileForm(firstName = u.firstName, lastName = u.lastName, email = u.email))
        return "layouts/settings/user/profile"
    }

    @PostMapping("/settings/{user}/profile")
    fun updateProfile(model: Model, @PathVariable user: String,
            @Valid updateProfileForm: UpdateProfileForm, bindingResult: BindingResult): String{

        if( !bindingResult.hasErrors() ){
            // TODO: user may be null
            var u = userRepo.findByUsername(user).get()
            u.firstName = updateProfileForm.firstName!!
            u.lastName = updateProfileForm.lastName!!
            u.email = updateProfileForm.email!!
            userRepo.save(u)
            model.addAttribute("updateProfileSuccessMessage", "Success!")
        }

        // TODO: user may be null
        val u = userRepo.findByUsername(user).get()
        model.addAttribute("user", u)

        return "layouts/settings/user/profile"
    }
}