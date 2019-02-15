package com.jahnelgroup.domain.user.group

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GroupRepo : JpaRepository<Group, Long>{

    fun findByGroupName(groupName: String): Optional<Group>

}