package com.jahnelgroup.controller

import com.jahnelgroup.domain.user.User
import com.jahnelgroup.domain.user.UserRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AdminController(private var userRepo: UserRepo){

    @ModelAttribute("createUser")
    fun emptyCreateUser() = User()

    /**
     * Admin searching for users in the system.
     */
    @GetMapping("/admin/users")
    fun users(model: Model, @RequestParam inputSearch: String?): String {
        if(inputSearch != null){
            model.addAttribute("searchResults", userRepo.searchUser(inputSearch))
            model.addAttribute("inputSearch", inputSearch)
        }
        return "layouts/admin/users"
    }

    /**
     * Admin creating a new user.
     */
    @PostMapping("/admin/user")
    fun createUser(model: Model, @ModelAttribute("createUser") user: User?){
        if( user != null ){
            user.enabled = true
            // TODO: Use Password Encoder
            userRepo.save(user)
        }
    }

    @GetMapping("/admin/groups")
    fun groups() = "layouts/admin/groups"

}