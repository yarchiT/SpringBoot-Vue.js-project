import toast from 'toastr';

toast.options = {
  "closeButton": false,
  "debug": false,
  "newestOnTop": false,
  "progressBar": false,
  "positionClass": "toast-top-right",
  "preventDuplicates": false,
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "1000",
  "timeOut": "5000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}




export default{
  showSuccess(message)
  {
    toast.success(message).attr('style', 'width: 150px !important');
  },
  showError(message)
  {
    toast.error(message).attr('style', 'width: 150px !important');
  }
}
