package com.jahnelgroup.controller.notification

import com.jahnelgroup.config.loggerFor
import com.jahnelgroup.domain.context.UserContextService
import com.jahnelgroup.domain.notification.Notification
import com.jahnelgroup.domain.notification.NotificationRepo
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class NotificationController(
        private var notificationRepo: NotificationRepo,
        private var userContextService: UserContextService) {

    val logger = loggerFor(NotificationController::class.java)

    /**
     * Ajax
     */
    @PostMapping(value = ["/api/notification"], consumes = ["application/json"], produces = ["application/json"])
    fun createMessage(@RequestBody notification: Notification): ResponseEntity<Notification> {
        logger.info("creating notification {}", notification)
        notificationRepo.save(notification)
        return ResponseEntity.ok().build()
    }

    /**
     * Ajax
     */
    @PutMapping(value = ["/api/notification/{notificationId}"], consumes = ["application/json"], produces = ["application/json"])
    fun updateNotification(@PathVariable notificationId: Long, @RequestBody notification: Notification): ResponseEntity<Notification> {
        var existingNotification = notificationRepo.findById(notificationId).get()
        existingNotification.isRead = notification.isRead
        logger.info("updating notification {}", existingNotification)
        notificationRepo.save(existingNotification)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/notificationList")
    fun getNotificationList(model: Model): String{
        model.addAttribute("notifications", notificationRepo.findAllByRecipient(userContextService.currentUsername()!!))
        return "fragments/notification :: notificationList"
    }

    @GetMapping("/notificationCount")
    fun getNotificationCount(model: Model) =
            ResponseEntity.ok().body(
                    notificationRepo
                            .findAllByRecipientAndIsRead(
                                    userContextService.currentUsername()!!, false).size)

}

