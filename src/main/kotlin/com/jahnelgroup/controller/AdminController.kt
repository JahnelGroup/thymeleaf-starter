package com.jahnelgroup.controller

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jahnelgroup.domain.user.User
import com.jahnelgroup.domain.user.UserAuthority
import com.jahnelgroup.domain.user.UserRepo
import com.jahnelgroup.service.AdminUserService

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.persistence.CascadeType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.NotNull

@Controller
class AdminController(private var userRepo: UserRepo,
    private var adminUserService: AdminUserService){

    /**
     * Create a new user form.
     */
    data class CreateUserForm (
            var username: String? = null,
            var firstName: String? = null,
            var lastName: String? = null,
            var email: String? = null,
            var password: String? = null,
            var passwordConfrim: String? = null
    ){
        fun toUser() = User(
            username = this.username!!,
            firstName = this.firstName!!,
            lastName = this.lastName!!,
            email = this.email!!,
            password = this.password!!
        )
    }

    @ModelAttribute("createUser")
    fun emptyCreateUser() = CreateUserForm()

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
    fun createUser(model: Model, @ModelAttribute("createUser") createUser: CreateUserForm): String{
        adminUserService.createUser(createUser.toUser())
        return "layouts/admin/users"
    }

    @GetMapping("/admin/groups")
    fun groups() = "layouts/admin/groups"

}