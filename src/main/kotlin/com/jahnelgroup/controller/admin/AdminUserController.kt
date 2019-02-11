package com.jahnelgroup.controller.admin

import com.jahnelgroup.controller.admin.form.CreateUserForm
import com.jahnelgroup.controller.admin.form.CreateUserFormValidator
import com.jahnelgroup.domain.user.UserRepo
import com.jahnelgroup.service.AdminUserService

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.validation.BindingResult


@Controller
class AdminUserController(private var userRepo: UserRepo,
                          private var adminUserService: AdminUserService){

//    @InitBinder
//    fun initBinder(webDataBinder: WebDataBinder) {
//        webDataBinder.addValidators(CreateUserFormValidator())
//    }

    @ModelAttribute("createUserForm")
    fun createUserForm() = CreateUserForm()

    /**
     * Admin creating a new user.
     */
    @PostMapping("/admin/user")
    fun createUser(model: Model, createUserForm: CreateUserForm, bindingResult: BindingResult): String{
        CreateUserFormValidator().validate(createUserForm, bindingResult)
        if( !bindingResult.hasErrors() ){
            adminUserService.createUser(createUserForm.toUser())
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