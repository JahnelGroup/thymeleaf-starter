package com.jahnelgroup.domain.preferences

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jahnelgroup.domain.AbstractEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Id
import com.jahnelgroup.domain.user.*

@Table(name = "preferences")
@Entity
data class Preferences(

        @field:Id
        var preferenceID: Int,

        var userID: Int,

        var userPreferencesCodeID: Int,

        var isEnabled: Boolean

)
        //: AbstractEntity(){

        //@JsonIgnore
        //@ManyToOne
        //var user: User? = null

//}