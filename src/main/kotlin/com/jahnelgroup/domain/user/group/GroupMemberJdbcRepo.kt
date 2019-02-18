package com.jahnelgroup.domain.user.group

import com.jahnelgroup.domain.user.User
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class GroupMemberJdbcRepo(private var jdbcTemplate: NamedParameterJdbcTemplate) {

    fun searchNonMembers(groupId: Long): List<User> =
            jdbcTemplate.query("""
                SELECT u.username, u.first_name, u.last_name, u.email
                FROM users u
                JOIN user_group_members ugm ON ugm.username = u.username
                WHERE ugm.group_id != :groupId
            """.trimIndent(),
                    MapSqlParameterSource(mapOf("groupId" to groupId)),
                    BeanPropertyRowMapper<User>(User::class.java))

    fun searchNonMembers(groupId: Long, term: String): List<User> =
            jdbcTemplate.query("""
                SELECT u.username, u.first_name, u.last_name, u.email
                FROM users u
                JOIN user_group_members ugm ON ugm.username = u.username
                WHERE ugm.group_id != :groupId AND (
                    lower(u.username) like %:term% or
                    lower(u.firstName) like %:term% or
                    lower(u.lastName) like %:term% or
                    lower(u.email) like %:term%
                )
            """.trimIndent(),
                    MapSqlParameterSource(mapOf("groupId" to groupId, "term" to term)),
                    BeanPropertyRowMapper<User>(User::class.java))

}