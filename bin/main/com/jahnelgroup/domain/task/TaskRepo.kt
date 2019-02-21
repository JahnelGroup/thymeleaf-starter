package com.jahnelgroup.domain.task

import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepo : JpaRepository<Task, Long>