var openEditTaskList = function(taskListId){
    console.log("taskListId " + taskListId);
    $.ajax({
        url: '/tasklist/'+taskListId,
        type: 'get',
        success: function(data, textStatus, xhr) {
            $("#editTaskListModal").modal("show");
            $('#editTaskListForm').replaceWith(data);
        }
    });

}


/* handlers to delegate all click events. */
const clickHandler = (event) => {
    const target = event.target

    /**
     * Settings > Account > Change Password Modal
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
                    //location.href = '/settings/account';
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
    
}

export { clickHandler }