package com.jahnelgroup.domain.user.preferences

import com.jahnelgroup.domain.AbstractEntity
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "preferences")
@Entity
data class Preferences(
        var name: String? = "",
        var description: String? = "",
        var value:  Boolean? = false,

        //@JsonIgnore
        //@field:OneToOne
        var user: String? = ""

): AbstractEntity()
