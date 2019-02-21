package com.jahnelgroup.domain.user.group

import com.jahnelgroup.domain.user.User
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class GroupMemberJdbcRepo(private var jdbcTemplate: NamedParameterJdbcTemplate) {

    fun searchMembers(groupId: Long): List<User> =
            jdbcTemplate.query("""
                SELECT u.username, u.first_name, u.last_name, u.email
                FROM users u
                JOIN user_group_members ugm on u.username = ugm.username
                LEFT JOIN user_group_members ugm2 ON u.username = ugm2.username AND ugm2.group_id = :groupId
                WHERE ugm.group_id = :groupId AND ugm2.id IS NOT NULL
            """.trimIndent(),
                    MapSqlParameterSource(mapOf("groupId" to groupId)),
                    BeanPropertyRowMapper<User>(User::class.java))

    fun searchNonMembers(groupId: Long): List<User> =
            jdbcTemplate.query("""
                SELECT distinct u.username, u.first_name, u.last_name, u.email
                FROM users u
                JOIN user_group_members ugm on u.username = ugm.username
                LEFT JOIN user_group_members ugm2 ON u.username = ugm2.username AND ugm2.group_id = :groupId
                WHERE ugm.group_id <> :groupId AND ugm2.id IS NULL
            """.trimIndent(),
                    MapSqlParameterSource(mapOf("groupId" to groupId)),
                    BeanPropertyRowMapper<User>(User::class.java))

    fun searchNonMembers(groupId: Long, term: String): List<User> =
            jdbcTemplate.query("""
                SELECT distinct u.username, u.first_name, u.last_name, u.email
                FROM users u
                JOIN user_group_members ugm on u.username = ugm.username
                LEFT JOIN user_group_members ugm2 ON u.username = ugm2.username AND ugm2.group_id = :groupId
                WHERE
                    (
                        lower(u.username) like :term or
                        lower(u.first_name) like :term or
                        lower(u.last_name) like :term or
                        lower(u.email) like :term
                    )
                    AND ugm.group_id <> :groupId AND ugm2.id IS NULL
            """.trimIndent(),
                    MapSqlParameterSource(mapOf("groupId" to groupId, "term" to "%$term%")),
                    BeanPropertyRowMapper<User>(User::class.java))

}