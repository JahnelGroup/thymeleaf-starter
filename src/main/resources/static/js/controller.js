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


/* handlers to delegate all click events. */
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
     * Home > Open Task List Modal
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
     * Marking a task as complete or incomplete.
     */
    else if(target.classList.contains('task-list-task-checkbox')) {
        updateTask(target);
    }

    /**
     * Refresh the tasklist when the edit button closes
     */
    else if(target.id == 'editTaskListModal' && target.className == 'modal fade' ){
        $.ajax({
            url: '/tasklists',
            type: 'get',
            success: function(data) {
                $('#task-lists').replaceWith(data);
            }
        });
    }

    else if(target.id == 'dummyTakeANoteTextbox'){
        $('#takeANoteUnfocused').hide()
        $('#takeANoteFocused').show()
        $('#newTaskDescription').focus()
    }
}

// For when clicking out of an updated field.
const blurHandler = (event) => {
    const target = event.target

    if (target.id == 'editTaskListModalLabel') {
        $.ajax({
            url: '/api/tasklist/'+$("#editTaskListFormTaskListId")[0].value,  // TODO: Why is this an array?
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
     * Changing a task text
     */
    else if ([...target.classList].includes('tasks-listItem-update')) {
        updateTask(target);
    }

    /**
     * Bluring out of add a new task
     */
    else if (target.id =='newTaskTitle' || target.id == 'newTaskDescription'){
        if(event.relatedTarget == null ||
            (event.relatedTarget.id != 'newTaskTitle' && event.relatedTarget.id != 'newTaskDescription') ){

            // TODO: Why are these arrays?
            var title = $("#newTaskTitle")[0].value;
            var description = $("#newTaskDescription")[0].value;

            if( title != null || description != null ){
                $.ajax({
                    url: '/api/task/',
                    contentType: "application/json",
                    type: 'post',
                    data: JSON.stringify({
                        "title": title,
                        "description": description
                    }),
                    success: function(data, textStatus, xhr) {
                        $('#takeANoteFocused').hide()
                        $('#takeANoteUnfocused').show()
                        reloadTaskList();
                    }
                });
            }else{
                $('#takeANoteFocused').hide()
                $('#takeANoteUnfocused').show()
            }
        }
    }
}

export { clickHandler, blurHandler }