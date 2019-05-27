package com.jahnelgroup.domain.task

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jahnelgroup.domain.AbstractEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "task_list_users")
@Entity
data class TaskListUser(

        var username: String? = null,

        @JsonIgnore
        @field:ManyToOne
        var taskList: TaskList? = null

): AbstractEntity()
