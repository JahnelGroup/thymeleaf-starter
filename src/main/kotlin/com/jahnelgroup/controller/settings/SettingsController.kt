package com.jahnelgroup.controller.settings

import com.jahnelgroup.domain.user.UserRepo
import com.jahnelgroup.service.context.UserContextService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SettingsController(private var userRepo: UserRepo, private var userContextService: UserContextService) {

    @GetMapping("/settings/users")
    fun users() = "layouts/settings/users"

    @GetMapping("/settings/groups")
    fun groups() = "layouts/settings/groups"
}