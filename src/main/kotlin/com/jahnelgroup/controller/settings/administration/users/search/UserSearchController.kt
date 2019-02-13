package com.jahnelgroup.controller.settings.administration.users.search

import com.jahnelgroup.domain.user.UserRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class UserSearchController(private var userRepo: UserRepo){

    /**
     * Admin searching for users in the system.
     */
    @GetMapping("/admin/users/search")
    fun users(model: Model, @RequestParam inputSearch: String?): String {
        if(inputSearch != null){
            model.addAttribute("searchResults", userRepo.searchUser(inputSearch))
            model.addAttribute("inputSearch", inputSearch)
        }
        return "layouts/settings/admin/users/search"
    }

}