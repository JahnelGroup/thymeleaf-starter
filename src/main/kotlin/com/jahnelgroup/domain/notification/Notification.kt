package com.jahnelgroup.domain.notification

import com.jahnelgroup.domain.AbstractEntity
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "notifications")
@Entity
data class Notification(
        var recipient: String? = null,
        var sender: String? = null,
        var content: String? = null,
        var isRead: Boolean = false
): AbstractEntity()
