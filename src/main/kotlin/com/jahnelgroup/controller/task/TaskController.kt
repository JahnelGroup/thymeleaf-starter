package com.jahnelgroup.controller.task

import com.jahnelgroup.domain.task.TaskList
import com.jahnelgroup.domain.task.TaskListRepo
import com.jahnelgroup.domain.task.TaskRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpServletResponse

@Controller
class TaskController(
        private var taskRepo: TaskRepo,
        private var taskListRepo: TaskListRepo) {

    /**
     * This is intended to be called asynchronously with ajax.
     */
    @GetMapping("/tasklist/{taskListId}")
    fun get(model: Model, @PathVariable taskListId: Long): String{
        model.addAttribute("taskList", taskListRepo.findById(taskListId).get())
        return "fragments/modals/editTaskList :: editTaskListForm"
    }

    @PostMapping("/tasklist/{taskListId}")
    fun post(model: Model, @PathVariable taskListId: Long, taskList: TaskList,
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