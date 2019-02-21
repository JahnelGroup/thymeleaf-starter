package com.jahnelgroup.controller.task

import com.jahnelgroup.config.loggerFor
import com.jahnelgroup.domain.task.Task
import com.jahnelgroup.domain.task.TaskListRepo
import com.jahnelgroup.domain.task.TaskRepo
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TaskRestController(
        private var taskRepo: TaskRepo,
        private var taskListRepo: TaskListRepo) {

    val logger = loggerFor(TaskController::class.java)

    @PostMapping(value = ["/task/{taskId}"], consumes = ["application/json"], produces = ["application/json"])
    fun postTask(@PathVariable taskId: Long, @RequestBody task: Task): ResponseEntity<Task> {
        // there are much better/safer ways to do this.
        task.taskList = taskRepo.findById(taskId).get().taskList
        taskRepo.save(task)
        logger.info("Updated {}", task)
        return ResponseEntity.ok().build<Task>()
    }

}