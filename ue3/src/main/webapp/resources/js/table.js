$(document).ready(function() {
  var positionMap =  {"0" : "#start_road", "1" : "#road_1", "2" : "#road_2", "3" : "#road_3", "4" : "#road_4", "5" : "#road_5", "6" : "#finish_road"};
  var altTextMap =  {"0" : "null", "1" : "eins", "2" : "zwei", "3" : "drei"};

  var currentPlayerPosition = [0,0];
  var gameFinished = false;

  var movePlayer = function(playerIdString, playerId, diceResult, finalPosition, callback) {
   $(playerIdString).fadeOut(700, function() {
     $(playerIdString).appendTo(positionMap[currentPlayerPosition[playerId] + diceResult]);
     console.log('Position: ' + currentPlayerPosition[playerId] + diceResult);
     console.log('Append to position: ' + positionMap[currentPlayerPosition[playerId] + diceResult]);

     currentPlayerPosition[playerId] = currentPlayerPosition[playerId] + diceResult;
     $(playerIdString).fadeIn(700, function() {
       if (currentPlayerPosition[playerId] !== finalPosition) {
         $(playerIdString).fadeOut(700, function() {
           $(playerIdString).appendTo(positionMap[finalPosition]);
           currentPlayerPosition[playerId] = finalPosition;
           $(playerIdString).fadeIn(700, function() {
             callback();
           });
         });
       } else {
         callback();
       }
     });
   });
  };

  var finishGame = function() {
   gameFinished = true;
   $("#diceImage").attr("src","img/wuerfel0.png");
  };

  var i = 1;

  var performDiceAnimation = function(response) {
    // jsp calls the callback function three times with ['begin', 'complete', 'success']
    console.log('jaaaa');
    if (response.status === 'success') {
      // set game data
      var playerDiceResult = parseInt($('#playerScore').html(),10);
      var computerDiceResult = parseInt($('#computerScore').html(),10);
      var playerPosition = parseInt($('#playerPosition').html(),10);
      var computerPosition = parseInt($('#computerPosition').html(),10);

      // set dice image and alt text
      $(".diceImage").attr("src","resources/img/wuerfel"+playerDiceResult+".png");
      $(".diceImage").attr("alt","W&uuml;rfel mit einer "+altTextMap[data.playerDiceResult]);

      // set cars

      //disable ajax request

      // move cars
      movePlayer('#player1', 0, playerDiceResult, playerPosition, function() {
        movePlayer('#player2', 1, computerDiceResult, computerPosition, function() {
          //enable ajax request

        });
      });


    }
  };

  var performError = function(response) {
    console.log(response);
    console.log('finish');
  };
});

