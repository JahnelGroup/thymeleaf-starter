package com.jahnelgroup.domain.preferences

import org.springframework.data.jpa.repository.JpaRepository

interface PreferencesRepo : JpaRepository<Preferences, Long>