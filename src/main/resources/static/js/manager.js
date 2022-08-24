$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();

    $('.nBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
       // var text = $(this).text(); //return New or Edit
       $('#addUser #name').val('');
       $('#addUser #pass').val('');
       $('#addUser #fullname').val('');
       $('#addUser #email').val('');
       $('#addUser #numberphone').val('');
       $('#addUser #gender').val('');
       $('#addUser #dateofbirth').val('');
       $('#addUser #avatar').val('');
       $('#addUser #idfunction').val('');
       $('#addUser').modal();
    });

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('.formEdit #pass').val(user.pass);
            $('.formEdit #fullname').val(user.fullname);
            $('.formEdit #email').val(user.email);
            $('.formEdit #numberphone').val(user.numberphone);
            $('.formEdit #gender').val(user.gender);
            $('.formEdit #dateofbirth').val(user.dateofbirth);
            $('.formEdit #avatar').val(user.avatar);
            $('.formEdit #idfunction').val(user.idfunction); 
            $('.formEdit #name').val(user.name);
        });
        $('#editUser').modal();
    });

    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#removeUser #dConfirm').attr('href', href);
        $('#removeUser').modal();
    });
});