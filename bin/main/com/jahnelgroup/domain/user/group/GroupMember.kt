package com.jahnelgroup.domain.user.group

import com.jahnelgroup.domain.AbstractEntity
import javax.persistence.*
import javax.validation.constraints.NotNull

@Table(name = "user_group_members")
@Entity
class GroupMember (

        var username: String,

        @field:NotNull
        @field:ManyToOne
        var group: Group

): AbstractEntity()