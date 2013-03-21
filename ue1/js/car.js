$(document).ready(function() {
  var car1 = $('#car1');

  var moveCar = function() {
    $('#car1').fadeOut(1000, function(){
      $('#pos1').html(car1);
      $('#car1').fadeIn(1000);

    });
  };

  $('#wuerfel img').click(moveCar);
});
