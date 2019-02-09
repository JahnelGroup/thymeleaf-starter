package com.jahnelgroup.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AdminController{

    @GetMapping("/admin/users")
    fun users() = "layouts/admin/users"

    @GetMapping("/admin/groups")
    fun groups() = "layouts/admin/groups"

}