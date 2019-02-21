package com.jahnelgroup.domain.user

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "users")
@Entity
data class User (

    @field:Id
    var username: String,

    var firstName: String,

    var lastName: String,

    var email: String,

    @get:JsonIgnore
    var password: String,

    @get:JsonIgnore
    var enabled: Boolean = true

)