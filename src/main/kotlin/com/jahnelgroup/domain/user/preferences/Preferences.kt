package com.jahnelgroup.domain.user.preferences

import com.jahnelgroup.domain.AbstractEntity
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "preferences")
@Entity
data class Preferences(
        var name: String?,
        var description: String?,
        var value: String?
): AbstractEntity()
