package com.jahnelgroup.domain.user.preferences

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jahnelgroup.domain.AbstractEntity
import com.jahnelgroup.domain.user.User
import javax.persistence.Entity
import javax.persistence.OneToOne
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
