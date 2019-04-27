/**
 * ==========================================
 * Common Functions
 * ==========================================
 */

var reloadTaskList = function(){
    $.ajax({
        url: '/tasklists',
        type: 'get',
        success: function(data) {
            $('#task-lists').replaceWith(data);
        }
    });
}

var openEditTaskList = function(taskListId){
    //$("#taskList"+taskListId).fadeTo('fast', 0.2)
    $.ajax({
        url: '/tasklist/'+taskListId+'/modal',
        type: 'get',
        success: function(data, textStatus, xhr) {
            $('#editTaskListModal').replaceWith(data);
            $("#editTaskListModal").modal("show");
        }
    });
}

var updateTask = function(target){
    var task = {
        "id": target.parentElement.firstElementChild.value,
        "description": target.parentElement.lastElementChild.value ?
            target.parentElement.lastElementChild.value : target.parentElement.lastElementChild.textContent,
        "completed": target.checked
    }

    $.ajax({
        url: '/api/task/'+task.id,
        contentType: "application/json",
        type: 'post',
        data: JSON.stringify(task),
        success: function(data, textStatus, xhr) {
            if( xhr.getResponseHeader("hasErrors") == "true" ){
                alert("Something went wrong.")
            }

            if( target.checked ){
                target.parentElement.lastElementChild.classList.add("task-complete")
                target.parentElement.lastElementChild.classList.add("text-muted")
            }else{
                target.parentElement.lastElementChild.classList.remove("task-complete")
                target.parentElement.lastElementChild.classList.remove("text-muted")
            }
        },
        error: function (data) {
            alert("Something went wrong.")
        }
    });
}

var createTaskList = function(){
    var title = $("#newTaskTitle")[0].value;
    var description = $("#newTaskDescription")[0].value;

    $("#newTaskTitle").val(null)
    $("#newTaskDescription").val(null)
    $('#takeANoteFocused').hide()
    $('#takeANoteUnfocused').show()

    if( (title != null && title !== "") || (description != null && description !== "") ){
        $.ajax({
            url: '/api/task/',
            contentType: "application/json",
            type: 'post',
            data: JSON.stringify({
                "title": title,
                "description": description
            }),
            success: function(data, textStatus, xhr) {
                reloadTaskList();
            }
        });
    }
}

/**
 * ==========================================
 * Click Handler
 * ==========================================
 */

const clickHandler = (event) => {
    const target = event.target

    /**
     * Settings > Account > Change Password Modal Save Button
     */
    if (target.id == 'updatePasswordFormSaveButton') {
        event.preventDefault();
        $.ajax({
            url: $('#updatePasswordForm').attr('action'),
            type: 'post',
            data: $('#updatePasswordForm').serialize(),
            success: function(data, textStatus, xhr) {
                if( xhr.getResponseHeader("hasErrors") == "true" ){
                    $('#updatePasswordForm').replaceWith(data);
                }else{
                    $('#updatePasswordFormCloseButton').trigger('click')
                    $('#updatePasswordSuccessToast').toast('show')
                }
            },
            error: function (data) {
                $('#updatePasswordForm').replaceWith(data);
            }
        });
    }

    /**
     * Open 'Edit Task List'
     */
    else if(target.classList.contains('task-list-card')) {
        openEditTaskList(target.firstElementChild.value)
    }
    else if(target.classList.contains('task-list-title') || target.classList.contains('task-list-task-wrapper')){
        openEditTaskList(target.parentElement.firstElementChild.value);
    }
    else if(target.classList.contains('task-list-task-description')){
        openEditTaskList(target.parentElement.parentElement.firstElementChild.value);
    }

    /**
     * Marking a task as 'complete' or 'incomplete'
     */
    else if(target.classList.contains('task-list-task-checkbox')) {
        updateTask(target);
    }

    /**
     * Reload task list on clicking away from modal -or- clicking the close button
     */
    else if((target.id == 'editTaskListModal' && target.className == 'modal fade')
        || target.id == 'editTaskListFormCloseButton' ){
        reloadTaskList();
    }

    /**
     * Delete an entire task list
     */
    else if(target.id == 'editTaskListFormDeleteButton'){
        $.ajax({
            url: '/api/tasklist/'+$("#editTaskListFormTaskListId")[0].value,
            type: 'delete',
            contentType: "application/json",
            success: function(data) {
                reloadTaskList();
                $("#editTaskListModal").modal("hide");
            }
        });
    }

    /**
     * Toggle 'Take a Note' Fragment to show the focused form.
     */
    else if(target.id == 'dummyTakeANoteTextbox'){
        $('#takeANoteUnfocused').hide()
        $('#takeANoteFocused').show()
        $('#newTaskDescription').focus()
    }
}


/**
 * ==========================================
 * Blur Handler
 * ==========================================
 */

const blurHandler = (event) => {
    const target = event.target

    /**
     * Update task list title
     */
    if (target.id == 'editTaskListModalLabel') {
        $.ajax({
            url: '/api/tasklist/'+$("#editTaskListFormTaskListId")[0].value,
            contentType: "application/json",
            type: 'post',
            data: JSON.stringify({"title": target.value}),
            success: function (data, textStatus, xhr) {

            },
            error: function (data) {
                alert("Something went wrong.")
            }
        });
    }

    /**
     * Update a task take
     */
    else if ([...target.classList].includes('tasks-listItem-update')) {
        updateTask(target);
    }

    /**
     * Create a new task / task list
     */
    else if (target.id =='newTaskTitle' || target.id == 'newTaskDescription'){
        if(event.relatedTarget == null ||
            (event.relatedTarget.id != 'newTaskTitle' && event.relatedTarget.id != 'newTaskDescription') ){
            createTaskList();
        }
    }
}

/**
 * ==========================================
 * Mouse Handler
 * ==========================================
 */

const mouseHandler = (event) => {
    const target = event.target
    if( target.classList.contains('task-list-card') ){
        //console.log(event.type + " " + target.id)
    }
}

/**
 * ==========================================
 * Key Handler
 * ==========================================
 */

const keyListener = (event) => {
    const target = event.target

    /**
     * Create a new task / task list when hitting enter
     */
    if(target.id == 'newTaskTitle' || target.id == 'newTaskDescription' ){
        if (event.keyCode === 13) { // enter
            event.preventDefault();
            createTaskList();
        }
    }

    /**
     * Add a new task to an existing task list
     */
    else if (target.id == 'newTaskInput' && event.type == 'keydown' ){
        var inp = String.fromCharCode(event.keyCode);
        if (/[a-zA-Z0-9-_ ]/.test(inp)){
            var taskListId = $("#editTaskListFormTaskListId")[0].value
            var newTaskIndex = $(".tasks-listItem-id").length;
            event.preventDefault();

            $.ajax({
                url: '/api/tasklist/'+taskListId+'/task',
                contentType: "application/json",
                type: 'post',
                data: JSON.stringify({description: event.key}),
                success: function(data, textStatus, xhr) {
                    if( xhr.getResponseHeader("hasErrors") == "true" ){
                        alert("Something went wrong.")
                    }

                    var newTaskId = data.id;
                    var newTaskDesc = data.description;

                    $("<div class='form-check my-2'>" +
                        "<input type='hidden' class='tasks-listItem-id' id='tasks"+newTaskIndex+".id' name='tasks["+newTaskIndex+"].id' value='"+newTaskId+"'>" +
                        "<input type='checkbox' class='form-check-input task-list-task-checkbox' id='tasks4.completed1' name='tasks["+newTaskIndex+"].completed' value='true'>" +
                        "<input type='hidden' name='_tasks["+newTaskIndex+"].completed' value='off'>" +
                        "<input type='search' id='taskDescriptionId"+newTaskId+"' class='no-border tasks-listItem-update' name='tasks["+newTaskIndex+"].description' id='tasks"+newTaskIndex+".description'>" +
                        "</div>").insertBefore("#newTaskDiv");

                    // add value after focusing to move focus to the end
                    $("#taskDescriptionId"+newTaskId).focus().val(newTaskDesc).keyup();
                },
                error: function (data) {
                    alert("Something went wrong.")
                }
            });
        }
    }
}

export { clickHandler, blurHandler, mouseHandler, keyListener }