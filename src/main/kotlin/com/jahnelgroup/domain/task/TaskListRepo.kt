package com.jahnelgroup.domain.task

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TaskListRepo : JpaRepository<TaskList, Long>{

    @Query(nativeQuery = true, value = """
        select * from task_lists tl
            left join shared_task_lists stl on tl.id = stl.task_list_id
            where stl.username = ?#{ principal?.username }
            or tl.created_by = ?#{ principal?.username }
    """)
    fun findByCurrentUser() : List<TaskList>

}
