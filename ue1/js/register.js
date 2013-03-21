$(document).ready(function() {
  var validRegisterName = false;
  var validRegisterPassword = false;
  var validRegisterDob = true;

  var validate = function() {
    if(validRegisterName && validRegisterPassword && validRegisterDob) {
      $('#registerBtn').prop("disabled", false);
    } else {
      $('#registerBtn').prop("disabled", true);
    }
  };
  validate();

  var validateRegisterName = function() {
    var registerElem = $('#registername');
    var registerNamelength = registerElem.val().length;
    if (registerNamelength < 4 || registerNamelength > 8) {
      $('#registernameerror').html('Der Benutzername muss mindestens 4 Zeichen und darf maximal 8 Zeichen enthalten.');
      registerElem.addClass('error');
      validRegisterName = false;
    } else {
      $('#registernameerror').html('');
      registerElem.removeClass('error');
      validRegisterName = true;
    }
    validate();
  };

  var validateRegisterPassword = function() {
    var registerElem = $('#registerpassword');
    var registerPassowrdlength = registerElem.val().length;
    if  (registerPassowrdlength < 4 || registerPassowrdlength > 8) {
      $('#registerpassworderror').html('Das Passwort muss mindestens 4 Zeichen und darf maximal 8 Zeichen enthalten.');
      registerElem.addClass('error');
      validRegisterPassword = false;
    } else {
      $('#registerpassworderror').html('');
      registerElem.removeClass('error');
      validRegisterPassword = true;
    }
    validate();
  };

  var valdateRegisterDob = function() {
    var registerElem = $('#registerdate');
    var registerDate = registerElem.val();
    var dateRegex =  /^((0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](19|20)[0-9][0-9])?$/;

    if(dateRegex.test(registerDate)) {
      $('#registerdateerror').html('');
      registerElem.removeClass('error');
      validRegisterDob = true;
    } else {
      $('#registerdateerror').html('Verwenden Sie bitte folgendes Datumsformat: dd.mm.yyyy (z.B. 24.12.2013).');
      registerElem.addClass('error');
      validRegisterDob = false;
    }
    validate();
  };

  $('#registername').keyup(validateRegisterName);
  $('#registerpassword').keyup(validateRegisterPassword);
  $('#registerdate').keyup(valdateRegisterDob);
});

