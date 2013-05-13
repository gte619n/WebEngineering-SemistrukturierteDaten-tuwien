$(document).ready(function() {
  var positionMap =  {"0" : "#start_road", "1" : "#road_1", "2" : "#road_2", "3" : "#road_3", "4" : "#road_4", "5" : "#road_5", "6" : "#finish_road"};
  var altTextMap =  {"0" : "null", "1" : "eins", "2" : "zwei", "3" : "drei"};

  var round = 0;
  var currentPlayerPosition = [0,0];
  var gameFinished = false;

  var movePlayer = function(playerIdString, playerId, diceResult, finalPosition, callback) {
    if (!gameFinished) {
      $(playerIdString).fadeOut(700, function() {
       $(playerIdString).appendTo(positionMap[currentPlayerPosition[playerId] + diceResult]);
       currentPlayerPosition[playerId] = currentPlayerPosition[playerId] + diceResult;
       $(playerIdString).fadeIn(700, function() {
        if (currentPlayerPosition[playerId] == 6 ) {
          finishGame();
        } else {
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
        }
       });
      });
    }
  };

  // Helper Functions

  var finishGame = function() {
   gameFinished = true;
   $(".diceImage").attr("src","resources/img/wuerfel0.png");
  };

  var disableDice = function() {
    $(".diceImage").attr("disabled", "true");
  };

  var enableDice = function() {
    $(".diceImage").removeAttr("disabled");
  };

  var startAnimation = function() {
    disableDice();
  };

  var finishAnimation = function() {
    enableDice();
  };

  var performDiceAnimation = function() {
    startAnimation();

    // set game data
    var round = parseInt($('#round').html(),10);
    var playerDiceResult = parseInt($('#playerScore').html(),10);
    var computerDiceResult = parseInt($('#computerScore').html(),10);
    var playerPosition = parseInt($('#playerPosition').html(),10);
    var computerPosition = parseInt($('#computerPosition').html(),10);

    // set dice image and alt text
    $(".diceImage").attr("src","resources/img/wuerfel"+playerDiceResult+".png");

    console.log(currentPlayerPosition);
    console.log('player pos: '+ playerPosition);
    console.log('copmuter pos: '+ computerPosition);

    // move cars
    movePlayer('#player1', 0, playerDiceResult, playerPosition, function() {
      movePlayer('#player2', 1, computerDiceResult, computerPosition, function() {
        finishAnimation();
      });
    });
  };

  var waitForAjax = function(callback) {
    var displayedRound = parseInt($('#round').html(),10);

    var interval = self.setInterval(function(){
      displayedRound = parseInt($('#round').html(),10);
      if (displayedRound > round) {
        callback();
        window.clearInterval(interval);
      }
    },200);
  };

  $(".diceImage").click(function() {
    waitForAjax(performDiceAnimation);
    // waitForAjax(function() {
    //   performDiceAnimation();
    // });
  });
});

