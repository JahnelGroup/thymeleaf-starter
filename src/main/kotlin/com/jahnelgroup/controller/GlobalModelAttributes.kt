package com.jahnelgroup.controller

import com.jahnelgroup.domain.context.UserContextService
import com.jahnelgroup.domain.notification.NotificationRepo
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute

@ControllerAdvice
class ModelControllerAdvice(var userContextService: UserContextService,
                            var notificationRepo: NotificationRepo){

    @ModelAttribute("userContextService")
    fun userContextService() = userContextService

    @ModelAttribute("currentUser")
    fun currentUser() = userContextService.currentUser()

    @ModelAttribute("notificationRepo")
    fun notificationRepo() = notificationRepo

}
