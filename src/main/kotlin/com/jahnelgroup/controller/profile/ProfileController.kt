package com.jahnelgroup.controller.profile

import com.jahnelgroup.domain.user.UserRepo
import com.jahnelgroup.domain.user.group.GroupRepo
import com.jahnelgroup.service.context.UserContextService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class ProfileController(
        private var userRepo: UserRepo,
        private var groupRepo: GroupRepo,
        private var userContextService: UserContextService) {

    @GetMapping("/profile")
    fun settings() = "forward:/profile/${userContextService.currentUsername()}"

    @GetMapping("/profile/{user}")
    fun profile(model: Model,  @PathVariable user: String): String{
        // TODO: user may be null
        val user = userRepo.findByUsername(user).get()
        model.addAttribute("user", user)
        model.addAttribute("groups", groupRepo.findAllByUsername(user.username))
        return "profile"
    }

}