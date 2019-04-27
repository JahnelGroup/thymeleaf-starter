package com.jahnelgroup.controller.admin.groups

import com.jahnelgroup.domain.user.group.GroupRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AdminGroupSearchController(private var groupRepo: GroupRepo){

    @GetMapping("/admin/groups")
    fun search(model: Model, @RequestParam inputSearch: String?): String {
        model.addAttribute("inputSearch", inputSearch)
        if(!inputSearch.isNullOrBlank()){
            model.addAttribute("searchResults", groupRepo.findByGroupNameLike("%$inputSearch%"))
        }else{
            model.addAttribute("searchResults", groupRepo.findAll())
        }
        return "layouts/admin/groups/search"
    }

}