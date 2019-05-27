package com.jahnelgroup.domain.notification

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface NotificationRepo : JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.recipient = ?#{ principal?.username } order by n.isRead, n.createdDatetime desc")
    fun findAllByRecipient(recipient: String): List<Notification>

    fun findAllByRecipientAndIsRead(recipient: String, boolean: Boolean): List<Notification>

}
