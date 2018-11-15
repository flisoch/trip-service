const identifierRegex = /^[a-z]\w{4,15}$/i;

const validatePassword = () => {
    let password = $('#password').get(0);
    passwordMatch(password);
};

const passwordMatch = (password) => {
    let passwordConfirmation = $('#confirm-password').get(0);
    if (password.value !== passwordConfirmation.value) {
        passwordConfirmation.setCustomValidity('passwords must match!');
        passwordConfirmation.reportValidity();
    } else {
        passwordConfirmation.setCustomValidity('');
    }
};

const validUsername = (id) => {
    let username = $(`#${id}`).get(0);
    if (!username.value.match(identifierRegex)) {
        username.setCustomValidity(`username must comply with regex ${identifierRegex}`);
        username.reportValidity();
    } else {
        username.setCustomValidity('');
        $.post('/check',{'username':username.value},function (data) {
            datat = data;
            if($.isEmptyObject(data)){
                username.setCustomValidity('username is already taken');
                username.reportValidity();
            }
        },'JSON');
    }

};

