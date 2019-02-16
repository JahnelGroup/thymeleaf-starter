package com.jahnelgroup.domain.user.group

import javax.persistence.*

@Table(name = "user_groups")
@Entity
data class Group (

        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var groupName: String,

        @field:OneToMany(fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)], orphanRemoval = true, mappedBy = "id.groupId", targetEntity = GroupAuthority::class)
        var authorities: MutableSet<GroupAuthority> = mutableSetOf()

        // Performance risk: Consider why you need this and how it will be used before adding it.
        // @field:OneToMany(fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)], orphanRemoval = true, mappedBy = "group")
        // var members: Set<GroupMember> = emptySet()
)