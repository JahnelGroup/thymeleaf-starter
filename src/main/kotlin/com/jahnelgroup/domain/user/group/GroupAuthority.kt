package com.jahnelgroup.domain.user.group

import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Embeddable
data class AuthorityId(
        var groupId: Long = 0,
        var authority: String = ""
) : Serializable

@Table(name = "user_group_authorities")
@Entity
data class GroupAuthority(
        @EmbeddedId
        var id: AuthorityId? = null
)