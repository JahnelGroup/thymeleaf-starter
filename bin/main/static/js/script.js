(function($){
    var $form = $('#updatePasswordForm');
    $form.on('submit', function(e) {
        e.preventDefault();
        $.ajax({
            url: $form.attr('action'),
            type: 'post',
            data: $form.serialize(),
            success: function(data) {
                $('#updatePasswordForm').load(data);
            },
            error: function (data) {
                $('#updatePasswordForm').load(data);
            }
        });
    })
}(jQuery));