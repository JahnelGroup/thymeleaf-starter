package com.jahnelgroup.controller.task

import com.jahnelgroup.config.loggerFor
import com.jahnelgroup.domain.task.Task
import com.jahnelgroup.domain.task.TaskList
import com.jahnelgroup.domain.task.TaskListRepo
import com.jahnelgroup.domain.task.TaskRepo
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

/**
 * AJAX Endpoints
 */
@Controller
class TaskController(
        private var taskRepo: TaskRepo,
        private var taskListRepo: TaskListRepo) {

    val logger = loggerFor(TaskController::class.java)

    @GetMapping("/tasklist/{taskListId}")
    fun getTaskList(model: Model, @PathVariable taskListId: Long): String{
        model.addAttribute("taskList", taskListRepo.findById(taskListId).get())
        return "fragments/modals/editTaskList :: editTaskListForm"
    }

    @PostMapping("/tasklist/{taskListId}")
    fun postTaskList(model: Model, @PathVariable taskListId: Long, taskList: TaskList,
             bindingResult: BindingResult, response: HttpServletResponse): String{

        if( bindingResult.hasErrors() ){
            response.setHeader("hasErrors", "true")
        }else{
            taskList.tasks.forEach {
                it.taskList = taskList
            }
            taskListRepo.save(taskList)
        }

        return "fragments/modals/editTaskList :: editTaskListForm"
    }



}