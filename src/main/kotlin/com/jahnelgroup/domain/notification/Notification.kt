package com.jahnelgroup.domain.notification

import com.jahnelgroup.domain.AbstractEntity
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "notifications")
@Entity
data class Notification(
        var recipient: String?,
        var sender: String?,
        var content: String?,
        var isRead: Boolean = false
): AbstractEntity()
