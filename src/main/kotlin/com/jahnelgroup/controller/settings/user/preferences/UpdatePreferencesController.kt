package com.jahnelgroup.controller.settings.user.profile

import com.jahnelgroup.domain.context.UserContextService
import com.jahnelgroup.domain.user.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class UpdatePreferencesController(
        private var userService: UserService,
        private var userContextService: UserContextService){

    // Forward is the same as redirect but the URL remains the same
    @GetMapping("/settings/preferences")
    fun profile() = "forward:/settings/${userContextService.currentUsername()}/preferences"

    @PreAuthorize("hasRole('ROLE_ADMIN') || #user == authentication.name")
    @GetMapping("/settings/{user}/preferences")
    fun profile(model: Model, @PathVariable user: String): String{
        val u = userService.findByUsername(user)
        model.addAttribute("user", u)
        //model.addAttribute("updatePreferencesForm",
        //        UpdatePreferencesForm(preferences = u.preferences))
        return "layouts/settings/user/preferences"
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || #user == authentication.name")
    @PostMapping("/settings/{user}/preferences")
    fun updatePreferences(model: Model, @PathVariable user: String,
                      @Valid updatePreferencesForm: UpdatePreferencesForm, bindingResult: BindingResult): String{

        //if( !bindingResult.hasErrors() ){
        //    userService.updatePreferences(
        //            user,
        //            updatePreferencesForm.preferences!!)

        //    model.addAttribute("updatePreferencesSuccessMessage", "Success!")
        //}
        model.addAttribute("updatePreferencesSuccessMessage", "Success!")
        model.addAttribute("user", userService.findByUsername(user))
        return "layouts/settings/user/preferences"
    }
}