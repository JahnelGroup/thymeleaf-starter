package com.jahnelgroup.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.*
import org.springframework.data.annotation.Version
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.*
import javax.persistence.Id

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @get:JsonIgnore
    @CreatedBy
    @Column(nullable = false, updatable = false)
    var createdBy: String = ""

    @get:JsonIgnore
    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDatetime: Instant = Instant.now()

    @get:JsonIgnore
    @LastModifiedBy
    @Column(nullable = false)
    var lastModifiedBy: String = ""

    @get:JsonIgnore
    @LastModifiedDate
    @Column(nullable = false)
    var lastModifiedDatetime: Instant = Instant.now()

    @Version
    @Column(nullable = false)
    var version: Long = 0

}