package com.jahnelgroup.domain.user

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Table(name = "users")
@Entity
data class User (

    @field:Id
    var username: String? = null,

    var firstName: String? = null,

    var lastName: String? = null,

    var email: String? = null,

    @get:JsonIgnore
    var password: String? = null,

    @get:JsonIgnore
    var enabled: Boolean? = null,

    @field:OneToMany(cascade = [(CascadeType.ALL)], orphanRemoval = true, mappedBy = "id.username", targetEntity = UserAuthority::class)
    var authorities: Set<UserAuthority>? = emptySet()

)