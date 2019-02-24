package com.jahnelgroup.controller.profile

import com.jahnelgroup.domain.context.UserContextService
import com.jahnelgroup.domain.user.UserService
import com.jahnelgroup.domain.user.group.GroupRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class ProfileController(
        private var userService: UserService,
        private var groupRepo: GroupRepo,
        private var userContextService: UserContextService) {

    @GetMapping("/profile")
    fun settings() = "forward:/profile/${userContextService.currentUsername()}"

    @GetMapping("/profile/{user}")
    fun profile(model: Model,  @PathVariable user: String): String{
        // TODO: user may be null
        model.addAttribute("user", userService.findByUsername(user))
        model.addAttribute("groups", groupRepo.findAllByUsername(user))
        return "profile"
    }

}