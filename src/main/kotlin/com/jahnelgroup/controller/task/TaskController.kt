package com.jahnelgroup.controller.task

import com.jahnelgroup.domain.task.TaskListRepo
import com.jahnelgroup.domain.task.TaskRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class TaskController(
        private var taskRepo: TaskRepo,
        private var taskListRepo: TaskListRepo) {

    /**
     * This is intended to be called asynchronously with ajax.
     */
    @PostMapping("/tasklist/{taskListId}")
    fun edit(model: Model, @PathVariable taskListId: String): String{
        return "fragments/modals/editTaskList :: editTaskListForm"
    }

}