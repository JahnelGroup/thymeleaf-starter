package com.jahnelgroup.controller

import com.jahnelgroup.domain.task.TaskListRepo
import com.jahnelgroup.domain.user.preferences.PreferenceRepo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class IndexController(private var taskListRepo: TaskListRepo, private var preferenceRepo: PreferenceRepo) {

    @ModelAttribute("taskListRepo")
    fun taskListRepo() = taskListRepo

    @ModelAttribute("preferenceRepo")
    fun preferenceRepo() = preferenceRepo

    @GetMapping("/")
    fun index() = "index"

}