package com.jahnelgroup.controller.task

import com.jahnelgroup.domain.task.TaskListRepo
import com.jahnelgroup.domain.task.TaskRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class TaskController(
        private var taskRepo: TaskRepo,
        private var taskListRepo: TaskListRepo) {

    /**
     * This is intended to be called asynchronously with ajax.
     */
    @GetMapping("/tasklist/{taskListId}")
    fun edit(model: Model, @PathVariable taskListId: Long): String{
        model.addAttribute("taskList", taskListRepo.findById(taskListId).get())
        return "fragments/modals/editTaskList :: editTaskListForm"
    }

}