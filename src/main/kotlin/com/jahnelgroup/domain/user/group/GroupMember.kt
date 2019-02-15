package com.jahnelgroup.domain.user.group

import javax.persistence.*
import javax.validation.constraints.NotNull

@Table(name = "user_group_members")
@Entity
class GroupMember (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private var id: Long? = null,

        var username: String,

        @field:NotNull
        @field:ManyToOne
        var group: Group
)