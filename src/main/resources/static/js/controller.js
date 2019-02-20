var openEditTaskList = function(taskListId){
    console.log("taskListId " + taskListId);
    $.ajax({
        url: '/tasklist/'+taskListId,
        type: 'get',
        success: function(data, textStatus, xhr) {
            $('#editTaskListModal').replaceWith(data);
            $("#editTaskListModal").modal("show");
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
    else if(target.id == 'editTaskListFormSaveButton'){
        event.preventDefault();
        $.ajax({
            url: $('#editTaskListForm').attr('action'),
            type: 'post',
            data: $('#editTaskListForm').serialize(),
            success: function(data, textStatus, xhr) {
                if( xhr.getResponseHeader("hasErrors") == "true" ){
                    $('#editTaskListForm').replaceWith(data);
                }else{
                    window.location = '/'
                }
            },
            error: function (data) {
                $('#editTaskListForm').replaceWith(data);
            }
        });
    }

    /**
     * Checking / Unchecking to Strike/UnStrike box
     */
    else if(target.classList.contains('task-list-task-checkbox')) {
        var task = {
            "id": target.parentElement.firstElementChild.value,
            "description": target.parentElement.lastElementChild.value,
            "completed": target.checked
        }

        $.ajax({
            url: '/api/task/'+task.id,
            contentType: "application/json",
            type: 'post',
            data: task,
            success: function(data, textStatus, xhr) {
                if( xhr.getResponseHeader("hasErrors") == "true" ){
                    alert("Something went wrong.")
                }

                if( target.checked ){
                    target.parentElement.lastElementChild.classList.add("task-complete")
                }else{
                    target.parentElement.lastElementChild.classList.remove("task-complete")
                }
            },
            error: function (data) {
                alert("Something went wrong.")
            }
        });
    }
}

export { clickHandler }