package com.jahnelgroup.domain.task

import org.springframework.data.jpa.repository.JpaRepository

interface TaskListRepo : JpaRepository<TaskList, Long>
