export function ModalCloser(modalName) {

    this.setModal = function (modalName) {
        this.modal = document.getElementById(`${modalName}-modal`);
        this.form = document.getElementById(`${modalName}-form`);
    };

    this.setModal(modalName);

    this.getCloseButtons = function() {
        return Array.from(this.modal.querySelectorAll(".modal-header-close, .modal-footer-close"));
    };

    this.clearValidityReport = function () {
        this.form.reset();
    }.bind(this);

    this.init = function () {
        this.getCloseButtons().forEach(button => button.addEventListener("click", this.clearValidityReport));
    };
}