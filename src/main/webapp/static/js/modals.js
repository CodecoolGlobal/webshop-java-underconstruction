export const modalCloser = {
    init() {
        this.closeButtons.forEach(button => button.addEventListener("click", this.clearValidityReport.bind(this)));
    },

    setAction(action) {
        this.modal = document.getElementById(`${action}-modal`);
        this.form = document.getElementById(`${action}-form`);
    },

    get closeButtons() {
        return Array.from(this.modal.querySelectorAll(".modal-header-close, .modal-footer-close"));
    },

    clearValidityReport() {
        this.form.reset();
    }
};