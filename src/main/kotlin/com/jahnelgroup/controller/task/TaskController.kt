package com.jahnelgroup.controller.task

import com.jahnelgroup.config.loggerFor
import com.jahnelgroup.domain.task.Task
import com.jahnelgroup.domain.task.TaskList
import com.jahnelgroup.domain.task.TaskListRepo
import com.jahnelgroup.domain.task.TaskRepo
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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

    /**
     * Ajax
     */
    @DeleteMapping(value = ["/api/tasklist/{taskListId}"], consumes = ["application/json"], produces = ["application/json"])
    fun deleteTaskList(@PathVariable taskListId: Long): ResponseEntity<Task> {
        taskListRepo.deleteById(taskListId)
        logger.info("Deleted {}", taskListId)
        return ResponseEntity.ok().build()
    }

    @PostMapping(value = ["/api/tasklist/{taskListId}"], consumes = ["application/json"], produces = ["application/json"])
    fun changeTaskListTitle(@PathVariable taskListId: Long, @RequestBody taskList: TaskList): ResponseEntity<TaskList> {
        var tl = taskListRepo.findById(taskListId).get()
        tl.title = taskList.title
        taskListRepo.save(tl)
        logger.info("Updated {}", tl)
        return ResponseEntity.ok().build()
    }

    /**
     * Ajax
     */
    @PostMapping(value = ["/api/task/{taskId}"], consumes = ["application/json"], produces = ["application/json"])
    fun updateTask(@PathVariable taskId: Long, @RequestBody task: Task): ResponseEntity<Task> {
        // there are much better/safer ways to do this.
        task.taskList = taskRepo.findById(taskId).get().taskList
        taskRepo.save(task)
        logger.info("Updated {}", task)
        return ResponseEntity.ok().build()
    }

    /**
     * Ajax
     */
    @PostMapping(value = ["/api/task"], consumes = ["application/json"], produces = ["application/json"])
    fun createTask(@RequestBody newTaskForm: NewTaskForm): ResponseEntity<Task> {
        var newTaskList = taskListRepo.save(TaskList(
                if( newTaskForm.title.isNullOrBlank() ) "Todo" else newTaskForm.title!!
        ))

        logger.info("Created newTaskList {}", newTaskList)

        if( !newTaskForm.description.isNullOrBlank() ){
            var newTask = Task(description = newTaskForm.description!!, completed = false)
            newTask.taskList = newTaskList
            taskRepo.save(newTask)
            logger.info("Created newTaskList {}", newTask)
        }

        return ResponseEntity.ok().build()
    }



}