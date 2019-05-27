package com.jahnelgroup.controller.task

import com.jahnelgroup.config.loggerFor
import com.jahnelgroup.domain.context.UserContextService
import com.jahnelgroup.domain.task.*
import com.jahnelgroup.domain.user.UserRepo
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
        private var taskListRepo: TaskListRepo,
        private var userRepo: UserRepo,
        private var userContextService: UserContextService) {

    val logger = loggerFor(TaskController::class.java)

    @GetMapping("/tasklist/{taskListId}/modal")
    fun getTaskListModal(model: Model, @PathVariable taskListId: Long): String{
        model.addAttribute("taskList", taskListRepo.findById(taskListId).get())
        return "fragments/task :: editTaskListModal"
    }

    @GetMapping("/tasklists")
    fun getTaskLists(model: Model): String{
        model.addAttribute("taskListRepo", taskListRepo)
        return "fragments/task :: taskLists"
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
    fun newTaskAndList(@RequestBody newTaskForm: NewTaskForm): ResponseEntity<Task> {
        var newTaskList = TaskList(
                if( newTaskForm.title.isNullOrBlank() ) "Todo" else newTaskForm.title!!
        )
        newTaskList.addTaskListUser(
                TaskListUser(
                        username = userContextService.currentUsername()!!,
                        taskList = newTaskList))
        newTaskList = taskListRepo.save(newTaskList)
        logger.info("Created new task list {}", newTaskList)
        if( !newTaskForm.description.isNullOrBlank() ){
            var newTask = Task(description = newTaskForm.description!!, completed = false)
            newTask.taskList = newTaskList
            taskRepo.save(newTask)
            logger.info("Created new task {}", newTask)
        }

        return ResponseEntity.ok().build()
    }

    /**
     * Ajax
     */
    @PostMapping(value = ["/api/tasklist/{taskListId}/task"], consumes = ["application/json"], produces = ["application/json"])
    fun newTaskExistingList(@PathVariable taskListId: Long, @RequestBody newTaskForm: NewTaskForm): ResponseEntity<Task> {
        var existingTaskList = taskListRepo.findById(taskListId).get()
        var newTask = Task(description = newTaskForm.description!!, completed = false)
        newTask.taskList = existingTaskList
        taskRepo.save(newTask)
        logger.info("Created new task {} for existing task list {}", newTask, existingTaskList.title)
        return ResponseEntity.ok().body(newTask)
    }

    @GetMapping("/tasklist/{taskListId}/shareModal")
    fun getShareTaskListModal(model: Model, @PathVariable taskListId: Long, @RequestParam inputSearch: String?): String{
        model.addAttribute("taskList", taskListRepo.findById(taskListId).get())
        return "fragments/task :: shareTaskListModal"
    }

    @GetMapping("/tasklist/{taskListId}/searchUsers")
    fun shareSearch(model: Model, @PathVariable taskListId: Long, @RequestParam inputSearch: String?): String {
        model.addAttribute("inputSearch", inputSearch)
        if(!inputSearch.isNullOrBlank()){
            model.addAttribute("searchResults", userRepo.searchUser(inputSearch!!))
        }
        return "fragments/task :: shareSearchResults"
    }

    @GetMapping("/tasklist/{taskListId}/userAccessModal")
    fun getTaskListUserAccessModal(model: Model, @PathVariable taskListId: Long, @RequestParam inputSearch: String?): String{
        model.addAttribute("taskList", taskListRepo.findById(taskListId).get())
        return "fragments/task :: userAccessModal"
    }

    /**
     * Ajax
     */
    @PostMapping(value = ["/api/tasklist/{taskListId}/user"], consumes = ["application/json"], produces = ["application/json"])
    fun addUserToTaskList(@PathVariable taskListId: Long, @RequestBody taskListUser: TaskListUser): ResponseEntity<TaskList> {
        val existingTaskList = taskListRepo.findById(taskListId).get()
        existingTaskList.addTaskListUser(taskListUser)
        taskListRepo.save(existingTaskList)
        logger.info("user {} added to taskList {}", taskListUser.username, existingTaskList.title)
        return ResponseEntity.ok().body(existingTaskList)
    }

}
