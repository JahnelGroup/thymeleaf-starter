package com.jahnelgroup.domain.user.preferences

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PreferenceRepo : JpaRepository<Preferences, Long> {
    @Query("select n from Preferences n where n.name = :name and user = ?#{ principal?.username }")
    fun findByPreferenceName(name:String): Preferences

}