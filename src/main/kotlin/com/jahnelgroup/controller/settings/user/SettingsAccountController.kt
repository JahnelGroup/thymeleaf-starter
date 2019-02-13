package com.jahnelgroup.controller.settings.user

import com.jahnelgroup.domain.user.UserRepo
import com.jahnelgroup.service.context.UserContextService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class SettingsAccountController(
        private var userRepo: UserRepo,
        private var userContextService: UserContextService){

    // Forward is the same as redirect but the URL remains the same
    @GetMapping("/settings/account")
    fun profile() = "forward:/settings/${userContextService.currentUsername()}/account"

    @GetMapping("/settings/{user}/account")
    fun profile(model: Model, @PathVariable user: String): String{
        model.addAttribute("user", userRepo.findByUsername(user).get())
        return "layouts/settings/user/account"
    }

}