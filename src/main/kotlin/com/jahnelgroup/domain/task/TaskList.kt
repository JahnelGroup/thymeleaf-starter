package com.jahnelgroup.domain.task

import com.jahnelgroup.domain.AbstractEntity
import com.jahnelgroup.domain.user.User
import java.util.ArrayList
import javax.persistence.*

@Table(name = "task_lists")
@Entity
data class TaskList(

        var title: String = ""

): AbstractEntity(){

        @OneToMany(
                fetch = FetchType.EAGER,
                cascade = [(CascadeType.ALL)],
                orphanRemoval = true,
                mappedBy = "taskList")
        var tasks: Set<Task> = mutableSetOf()

}
