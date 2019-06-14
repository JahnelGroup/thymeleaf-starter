package com.jahnelgroup.domain.user.group

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface GroupRepo : JpaRepository<Group, Long>{

    fun findByGroupName(groupName: String): Optional<Group>

    fun findByGroupNameLike(groupName: String): List<Group>

    @Query("""SELECT g
                    FROM Group g JOIN GroupMember gm ON g.id = gm.group.id
                    WHERE gm.username = :username""")
    fun findAllByUsername(@Param("username") username: String): List<Group>

}