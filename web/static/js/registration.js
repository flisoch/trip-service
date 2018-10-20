
const validatePassword = () => {
    let password = $('#password').get(0);
    passwordMatch(password);
};

const passwordMatch = (password) => {
    let passwordConfirmation = $('#confirm-password').get(0);
    if (password.value != passwordConfirmation.value) {
        passwordConfirmation.setCustomValidity("Пароли не совпадают!");
    } else {
        passwordConfirmation.setCustomValidity('');
    }
};

const validUsername = (id) => {
    let username = $(`#${id}`).get(0);
    
}

