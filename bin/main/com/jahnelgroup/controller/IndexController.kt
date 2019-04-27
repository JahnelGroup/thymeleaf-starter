package com.jahnelgroup.controller

import com.jahnelgroup.domain.task.TaskListRepo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class IndexController(private var taskListRepo: TaskListRepo) {

    @ModelAttribute("taskListRepo")
    fun taskListRepo() = taskListRepo

    @GetMapping("/")
    fun index() = "index"

}