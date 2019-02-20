package com.jahnelgroup.controller.task

data class EditTaskListForm(
    var title: String? = null,
    var checkedTaskIds: List<Int> = emptyList(),
    var tasks: Set<Map<Long, String>> = emptySet()
)