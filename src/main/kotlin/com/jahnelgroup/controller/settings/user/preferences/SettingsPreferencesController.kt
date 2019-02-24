package com.jahnelgroup.controller.settings.user.preferences

import com.jahnelgroup.domain.context.UserContextService
import com.jahnelgroup.domain.user.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class SettingsPreferencesController(
        private var userService: UserService,
        private var userContextService: UserContextService){

    // Forward is the same as redirect but the URL remains the same
    @GetMapping("/settings/preferences")
    fun profile() = "forward:/settings/${userContextService.currentUsername()}/preferences"

    @GetMapping("/settings/{user}/preferences")
    fun profile(model: Model, @PathVariable user: String): String{
        model.addAttribute("user", userService.findByUsername(user))
        return "layouts/settings/user/preferences"
    }

}