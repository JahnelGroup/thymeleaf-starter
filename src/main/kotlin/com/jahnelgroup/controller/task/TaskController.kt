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

    @GetMapping("/tasklist/{taskListId}/modal")
    fun getTaskListModal(model: Model, @PathVariable taskListId: Long): String{
        model.addAttribute("taskList", taskListRepo.findById(taskListId).get())
        return "fragments/modals/editTaskList :: editTaskListForm"
    }

    @GetMapping("/tasklists")
    fun getTaskLists(model: Model): String{
        model.addAttribute("taskListRepo", taskListRepo)
        return "fragments/taskLists :: taskLists"
    }

    @PostMapping(value = ["/api/tasklist/{taskListId}"], consumes = ["application/json"], produces = ["application/json"])
    fun changeTaskListTitle(@PathVariable taskListId: Long, @RequestBody taskList: TaskList): ResponseEntity<TaskList> {
        var tl = taskListRepo.findById(taskListId).get()
        tl.title = taskList.title
        taskListRepo.save(tl)

        return ResponseEntity.ok().build()
    }

    @PostMapping(value = ["/api/task/{taskId}"], consumes = ["application/json"], produces = ["application/json"])
    fun postTask(@PathVariable taskId: Long, @RequestBody task: Task): ResponseEntity<Task> {
        // there are much better/safer ways to do this.
        task.taskList = taskRepo.findById(taskId).get().taskList
        taskRepo.save(task)
        logger.info("Updated {}", task)
        return ResponseEntity.ok().build()
    }


}