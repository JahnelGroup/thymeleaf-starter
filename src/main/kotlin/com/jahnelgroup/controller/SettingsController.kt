package com.jahnelgroup.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SettingsController {

    @GetMapping("/settings")
    fun index() = "settings"

}