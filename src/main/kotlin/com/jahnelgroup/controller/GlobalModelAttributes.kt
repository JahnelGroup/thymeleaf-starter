package com.jahnelgroup.controller

import com.jahnelgroup.service.context.UserContextService
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute

@ControllerAdvice
class ModelControllerAdvice(var userContextService: UserContextService){

    @ModelAttribute("currentUser")
    fun currentUser() = userContextService.currentUser()

}