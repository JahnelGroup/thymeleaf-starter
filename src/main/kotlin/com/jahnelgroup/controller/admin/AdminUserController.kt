package com.jahnelgroup.controller.admin

import com.jahnelgroup.domain.user.UserRepo
import com.jahnelgroup.service.AdminUserService

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import javax.validation.Valid
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors




@Controller
class AdminUserController(private var userRepo: UserRepo,
                          private var adminUserService: AdminUserService){

//    @InitBinder
//    fun initBinder(webDataBinder: WebDataBinder) {
//        webDataBinder.addValidators(CreateUserValidator())
//    }

    @ModelAttribute("createUser")
    fun emptyCreateUser() = CreateUserForm()

    /**
     * Admin creating a new user.
     */
    @PostMapping("/admin/user")
    fun createUser(model: Model, createUser: CreateUserForm): String{
        val target = BeanPropertyBindingResult(createUser, "user")
        CreateUserValidator().validate(createUser, target)
        if( !target.hasErrors() ){
            adminUserService.createUser(createUser.toUser())
        }
        return "layouts/admin/users"
    }

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

}