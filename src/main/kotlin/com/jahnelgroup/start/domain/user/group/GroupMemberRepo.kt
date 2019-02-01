package com.jahnelgroup.start.domain.user.group

import org.springframework.data.jpa.repository.JpaRepository

interface GroupMemberRepo : JpaRepository<GroupMember, Long>

