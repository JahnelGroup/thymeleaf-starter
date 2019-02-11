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

    var email: String,

    @get:JsonIgnore
    var password: String,

    @get:JsonIgnore
    var enabled: Boolean = true,

    @field:OneToMany(cascade = [(CascadeType.ALL)], orphanRemoval = true, mappedBy = "id.username", targetEntity = UserAuthority::class)
    var authorities: MutableSet<UserAuthority> = mutableSetOf()

){
    fun isAdmin(): Boolean = authorities.any {
        it.id?.authority.equals("ROLE_ADMIN")
    }

    fun addAuthority(authority: String){
        authorities.add(UserAuthority(AuthorityId(username,authority)))
    }
}