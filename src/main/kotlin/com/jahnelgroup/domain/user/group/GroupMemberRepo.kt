package com.jahnelgroup.domain.user.group

import org.springframework.data.jpa.repository.JpaRepository

interface GroupMemberRepo : JpaRepository<GroupMember, Long>{

    fun findByUsername(username: String): List<GroupMember>

}

