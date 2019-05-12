package com.jahnelgroup.domain.user.preferences

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PreferenceRe : JpaRepository<Preferences, Long> {
    @Query("select n from Preferences n where n.name = :name and n.createdBy = ?#{ principal?.username }")
    fun findByPreferenceName(name:String): List<Preferences>

}