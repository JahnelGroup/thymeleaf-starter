package com.jahnelgroup.controller

import com.jahnelgroup.domain.user.UserRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AdminController(private var userRepo: UserRepo){

    @GetMapping("/admin/users")
    fun users(model: Model, @RequestParam inputSearch: String?): String {
        if(inputSearch != null){
            model.addAttribute("searchResults", userRepo.searchUser(inputSearch))
            model.addAttribute("inputSearch", inputSearch)
        }
        return "layouts/admin/users"
    }

    @GetMapping("/admin/groups")
    fun groups() = "layouts/admin/groups"

}