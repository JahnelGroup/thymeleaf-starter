package com.jahnelgroup.controller.admin.users

import com.jahnelgroup.domain.user.UserRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AdminUserSearchController(private var userRepo: UserRepo){

    @GetMapping("/admin")
    fun search() = "redirect:/admin/users"

    @GetMapping("/admin/users")
    fun search(model: Model, @RequestParam inputSearch: String?): String {
        model.addAttribute("inputSearch", inputSearch)
        if(!inputSearch.isNullOrBlank()){
            model.addAttribute("searchResults", userRepo.searchUser(inputSearch!!))
        }else{
            model.addAttribute("searchResults", userRepo.findAll())
        }
        return "layouts/admin/users/search"
    }

}