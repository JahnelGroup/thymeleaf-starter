package com.jahnelgroup.controller.profile

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ProfileController {

    @GetMapping("/profile")
    fun profile() = "profile"

}