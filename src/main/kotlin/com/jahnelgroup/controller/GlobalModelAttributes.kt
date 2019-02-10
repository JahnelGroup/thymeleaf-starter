package com.jahnelgroup.controller

import com.jahnelgroup.service.context.UserContextService
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.method.HandlerMethod
import javax.servlet.http.HttpServletRequest



@ControllerAdvice
class ModelControllerAdvice(var userContextService: UserContextService){

    @ModelAttribute("currentUser")
    fun currentUser() = userContextService.currentUser()

    @ModelAttribute("requestURI")
    fun currentControlelr(model: Model): String{
        val currentRequest = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        return currentRequest.contextPath
    }

}