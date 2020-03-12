export const submitterBase = {

    setAction(action) {
        this.action = action;
        this.modal = document.getElementById(`${action}-modal`);
        this.form = document.getElementById(`${action}-form`);
        this.submitButton = this.modal.querySelector(".modal-footer-submit");
        this.url = `/user?action=${action}`;
    },

    init() {
        const button = this.submitButton;
        button.addEventListener("click", this.submit.bind(this));
    },

    get allInputs() {
        return Array.from(this.form.querySelectorAll("input"));
    },

    get username() {
        return document.getElementById(`${this.action}-username`);
    },

    get password() {
        return document.getElementById(`${this.action}-password`);
    },

    validateByHtmlAttributes() {
        return this.allInputs.every(input => {
            const result = input.checkValidity();
            if (result === false) {
                input.reportValidity();
            }
            return result;
        });
    },

    submit() {
    },

    collectData() {
        return {
            username: this.username.value,
            password: this.password.value
        }
    },

    publishCustomValidity(input, message) {
        input.setCustomValidity(message);
        input.reportValidity();
        input.setCustomValidity("");
    }
};