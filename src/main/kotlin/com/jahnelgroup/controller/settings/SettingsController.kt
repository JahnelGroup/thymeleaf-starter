package com.jahnelgroup.controller.settings

import com.jahnelgroup.domain.user.UserRepo
import com.jahnelgroup.service.context.UserContextService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class SettingsController(private var userRepo: UserRepo, private var userContextService: UserContextService) {

    @GetMapping("/settings")
    fun user() = "redirect:/settings/${userContextService.currentUsername()}"

    @GetMapping("/settings/{user}")
    fun user(model: Model, @PathVariable user: String): String{
        model.addAttribute("user", userRepo.findByUsername(user).get())
        return "layouts/settings/account"
    }

    @GetMapping("/settings/preferences")
    fun preferences() = "layouts/settings/preferences"

    @GetMapping("/settings/users")
    fun users() = "layouts/settings/users"

    @GetMapping("/settings/groups")
    fun groups() = "layouts/settings/groups"
}