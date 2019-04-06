package com.jahnelgroup.domain.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jahnelgroup.domain.AbstractEntity
import org.springframework.data.annotation.*
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import com.jahnelgroup.domain.preferences.Preferences

@Table(name = "users")
@Entity
data class User (

    @field:Id
    var username: String,

    var firstName: String,

    var lastName: String,

    var email: String,

    //@Column(nullable = true, updatable = true)
    //var preferences: Preferences?,

    @get:JsonIgnore
    var password: String,

    @get:JsonIgnore
    var enabled: Boolean = true,

    @get:JsonIgnore
    @CreatedBy
    @Column(nullable = false, updatable = false)
    var createdBy: String = "",

    @get:JsonIgnore
    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDatetime: Instant = Instant.now(),

    @get:JsonIgnore
    @LastModifiedBy
    @Column(nullable = false)
    var lastModifiedBy: String = "",

    @get:JsonIgnore
    @LastModifiedDate
    @Column(nullable = false)
    var lastModifiedDatetime: Instant = Instant.now(),

    @Version
    @Column(nullable = false)
    var version: Long = 0

)