package com.jahnelgroup.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface UserRepo : JpaRepository<User, Long> {

    fun findByUsername(username: String): Optional<User>
    //fun findByUsernameContainingIgnoreCase(username: String): List<User>

    @Query("""
        select u from User u where
        lower(u.username) like %:term% or
        lower(u.firstName) like %:term% or
        lower(u.lastName) like %:term% or
        lower(u.email) like %:term%""")
    fun searchUser(@Param("term") term: String): List<User>

    @Query(nativeQuery = true, value = """
        SELECT *
        FROM users u
        JOIN user_group_members ugm ON ugm.username = u.username
        WHERE ugm.group_id = :groupId
    """)
    fun findByGroupId(groupId: Long): List<User>

}
