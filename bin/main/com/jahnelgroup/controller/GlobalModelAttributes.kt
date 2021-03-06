package com.jahnelgroup.controller

import com.jahnelgroup.domain.context.UserContextService
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute

@ControllerAdvice
class ModelControllerAdvice(var userContextService: UserContextService){

    @ModelAttribute("userContextService")
    fun userContextService() = userContextService

    @ModelAttribute("currentUser")
    fun currentUser() = userContextService.currentUser()

}