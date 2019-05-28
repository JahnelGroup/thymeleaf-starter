package com.jahnelgroup.controller

import com.jahnelgroup.domain.task.TaskService
import com.jahnelgroup.domain.user.preferences.PreferenceRepo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class IndexController(private var taskService: TaskService, private var preferenceRepo: PreferenceRepo) {

    @ModelAttribute("taskService")
    fun taskService() = taskService

    @ModelAttribute("preferenceRepo")
    fun preferenceRepo() = preferenceRepo

    @GetMapping("/")
    fun index() = "index"

}