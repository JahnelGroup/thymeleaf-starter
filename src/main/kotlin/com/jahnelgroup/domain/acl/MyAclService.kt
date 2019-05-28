package com.jahnelgroup.domain.acl

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.security.acls.domain.ObjectIdentityImpl
import org.springframework.security.acls.domain.PrincipalSid
import org.springframework.security.acls.model.MutableAcl
import org.springframework.security.acls.model.MutableAclService
import org.springframework.security.acls.model.NotFoundException
import org.springframework.stereotype.Service
import java.io.Serializable
import javax.transaction.Transactional
import kotlin.reflect.KClass
import org.springframework.security.acls.model.ObjectIdentity
import java.sql.ResultSet

@Service
@Transactional
class MyAclService(
        private var jdbcTemplate: JdbcTemplate,
        private var aclService: MutableAclService
) {

    fun getObjectsWithAccess(clazz: Class<*>, username: String): List<ObjectIdentity>? {
        val args = arrayOf<Any>(username, clazz.name)
        val objects = jdbcTemplate.query("""
            SELECT
                obj.object_id_identity AS obj_id,
                class.class AS class
            FROM
                acl_object_identity obj,
                acl_class class,
                acl_entry entry
            WHERE
                obj.object_id_class = class.id
                and entry.granting = true
                and entry.acl_object_identity = obj.id
                and entry.sid = (SELECT id FROM acl_sid WHERE sid = ?)
                and obj.object_id_class = (SELECT id FROM acl_class WHERE acl_class.class = ?)
            GROUP BY
                obj.object_id_identity,
                class.class
        """, args, RowMapper<ObjectIdentity> { rs, _ ->
            val javaType = rs.getString("class")
            val identifier = rs.getLong("obj_id")
            ObjectIdentityImpl(javaType, identifier)
        })
        return if (objects.size == 0) null else objects
    }

    fun getObjectsWithAccessByOtherUsers(clazz: Class<*>, username: String): List<ObjectIdentity>? {
        val args = arrayOf<Any>(username, clazz.name)
        val objects = jdbcTemplate.query("""
            SELECT
                obj.object_id_identity AS obj_id,
                class.class AS class
            FROM
                acl_object_identity obj,
                acl_class class,
                acl_entry entry
            WHERE
                obj.object_id_class = class.id
                and entry.granting = true
                and entry.acl_object_identity = obj.id
                and entry.sid != (SELECT id FROM acl_sid WHERE sid = ?)
                and obj.object_id_class = (SELECT id FROM acl_class WHERE acl_class.class = ?)
            GROUP BY
                obj.object_id_identity,
                class.class
        """, args, RowMapper<ObjectIdentity> { rs, _ ->
            val javaType = rs.getString("class")
            val identifier = rs.getLong("obj_id")
            ObjectIdentityImpl(javaType, identifier)
        })
        return if (objects.size == 0) null else objects
    }

    fun find(javaType: KClass<out Any>, identifier: Serializable, username: String){
        val oi = ObjectIdentityImpl(javaType.java, identifier)
        val sid = PrincipalSid(username)

        val acl = aclService.readAclById(oi, listOf(sid))

    }

    fun upsertAces(javaType: KClass<out Any>, identifier: Serializable, vararg upsertAces: MyAce){
        val oi = ObjectIdentityImpl(javaType.java, identifier)

        var acl: MutableAcl? = null
        try {
            acl = aclService.readAclById(oi) as MutableAcl
        } catch (nfe: NotFoundException) {
            acl = aclService.createAcl(oi)
        }

        // might need to do this in some scenarios?
        //deleteAllAclEntries(acl!!)

        upsertAces.forEach { ace ->
            val sid = PrincipalSid(ace.username)
            ace.permissions.forEach { permission ->
                acl!!.insertAce(acl.entries.size, permission, sid, true)
            }
        }

        aclService.updateAcl(acl)
    }

    private fun deleteAllAclEntries(acl: MutableAcl) {
        val size = acl.entries.size
        for (i in 0 until size) acl.deleteAce(0)
    }



}