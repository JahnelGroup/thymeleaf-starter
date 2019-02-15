/* handlers to delegate all click events. */
const clickHandler = (event) => {
    const target = event.target

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
}

export { clickHandler }