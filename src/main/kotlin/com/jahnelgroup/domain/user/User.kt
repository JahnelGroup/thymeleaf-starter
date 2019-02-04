package com.jahnelgroup.domain.user

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Table(name = "users")
@Entity
data class User (

    @field:Id
    var username: String,

    var firstName: String,

    var lastName: String,

    @get:JsonIgnore
    var password: String,

    @get:JsonIgnore
    var enabled: Boolean,

    @field:OneToMany(cascade = [(CascadeType.ALL)], orphanRemoval = true, mappedBy = "id.username", targetEntity = UserAuthority::class)
    var authorities: Set<UserAuthority> = emptySet()

)