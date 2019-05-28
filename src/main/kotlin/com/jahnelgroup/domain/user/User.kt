package com.jahnelgroup.domain.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jahnelgroup.domain.task.TaskList
import org.springframework.data.annotation.*
import org.springframework.data.annotation.Version
import java.time.Instant
import javax.persistence.*
import javax.persistence.Id

@Table(name = "users")
@Entity
data class User(

        @field:Id
        var username: String = "",

        var firstName: String = "",

        var lastName: String = "",

        var email: String = "",

        @get:JsonIgnore
        var password: String = "",

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