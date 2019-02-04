package com.jahnelgroup.domain.user

import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Embeddable
data class AuthorityId(
    var username: String = "",
    var authority: String = ""
) : Serializable

@Table(name = "authorities")
@Entity
data class UserAuthority(
    @EmbeddedId
    var id: AuthorityId? = null
)
