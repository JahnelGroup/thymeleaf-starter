package com.jahnelgroup.domain.task

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TaskListRepo : JpaRepository<TaskList, Long>{

    @Query("select distinct tl " +
            "from TaskList tl " +
            "left join TaskListUser tlu " +
            "on tl.id = tlu.taskList.id " +
            "where tlu.username = ?#{ principal?.username } " +
            "or tl.createdBy = ?#{ principal?.username }")
    fun findByCurrentUser() : List<TaskList>

}
