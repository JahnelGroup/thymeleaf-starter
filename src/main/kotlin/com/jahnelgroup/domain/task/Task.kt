package com.jahnelgroup.domain.task

import com.jahnelgroup.domain.AbstractEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "tasks")
@Entity
data class Task(

        var description: String,

        var completed: Boolean,

        @field:ManyToOne
        var taskList: TaskList

): AbstractEntity()