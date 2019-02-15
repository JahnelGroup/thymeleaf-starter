package com.jahnelgroup.controller.admin.users.add

import com.jahnelgroup.config.loggerFor
import com.jahnelgroup.domain.user.UserRepo
import com.jahnelgroup.service.UserService
import com.jahnelgroup.validator.PasswordComplexityValidator
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class AddUserController(private var userRepo: UserRepo,
                        private var adminUserService: UserService){

    val logger = loggerFor(AddUserController::class.java)

    @ModelAttribute("createUserForm")
    fun createUserForm() = CreateUserForm()

    @ModelAttribute("emailInvitationForm")
    fun emailInvitationForm() = EmailInvitationForm()

    @GetMapping("admin/users/add")
    fun get() = "layouts/admin/users/add"

    /**
     * Admin inviting a new user.
     *
     * Note: If BindingResult isn't injected here then upon error they would redirect back to /admin/users/add/invite,
     * which is not what we want in this case.
     */
    @PostMapping("/admin/users/add/invite")
    fun invite(model: Model, @Valid emailInvitationForm: EmailInvitationForm, bindingResult: BindingResult): String {
        if( !bindingResult.hasErrors() ){
            logger.info("TODO: Send email invitation to {}", emailInvitationForm.email)
            model.addAttribute("inviteSuccessMessage", "Success!")
        }
        return "layouts/admin/users/add"
    }

    /**
     * Admin creating a new user.
     */
    @PostMapping("/admin/users/add/create")
    fun createUser(model: Model, createUserForm: CreateUserForm, bindingResult: BindingResult): String{
        if( !bindingResult.hasErrors() ){
            PasswordComplexityValidator().validate(createUserForm, bindingResult)
            if( !bindingResult.hasErrors() ){
                adminUserService.createUser(createUserForm.toUser())
                model.addAttribute("createSuccessMessage", "Success!")
            }
        }
        return "layouts/admin/users/add"
    }





}