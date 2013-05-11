//<![CDATA[

var i = 1;

var performDice = function(response) {
  // jsp calls the callback function three times with ['begin', 'complete', 'success']
  if (response.status === 'success') {
    // var playerDiceResult =
    // var computerDiceResult =
    // var playerPosition =
    // var computerPosition =
    console.log(i++);
    // $("#diceImage").attr("src","img/wuerfel"+data.player1DiceResult+".png");
    // $("#diceImage").attr("alt","W&uuml;rfel mit einer "+altTextMap[data.player1DiceResult]);
    movePlayer('#player1', 0, $('#playerScore').html(), $('#playerPosition').html(), function() {
      movePlayer('#player2', 1, $('#computerScore').html(), $('#computerPosition').html(), function() {
        completeAnimation();
        moveFinished = true;
      });
    });


  }
};

var performError = function() {
  console.log('finish');
};


/*
      AAAAAAAAAAAAL THE COEEEEEEEEEEEEEEEEEE
 */

 var positionMap =  {"0" : "#start_road", "1" : "#road_1", "2" : "#road_2", "3" : "#road_3", "4" : "#road_4", "5" : "#road_5", "6" : "#finish_road"};
 var altTextMap =  {"0" : "null", "1" : "eins", "2" : "zwei", "3" : "drei"};

 var currentPlayerPosition = [0,0];
 var gameFinished = false;
 var moveFinished = true;

 var movePlayer = function(playerIdString, playerId, diceResult, finalPosition, callback) {
   $(playerIdString).fadeOut(700, function() {
     $(playerIdString).appendTo(positionMap[currentPlayerPosition[playerId] + diceResult]);
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

 function finishGame() {
   gameFinished = true;
   $("#diceImage").attr("src","img/wuerfel0.png");


 }

 // call this function once before starting the animations
 function prepareAnimation() {
   $("#animationDone").remove();
 }

 // call this function once after all animations have finished
 function completeAnimation() {
   var div = $(document.createElement('div'));
   div.attr('id', 'animationDone');
   div.addClass('hide');
   $("body").append(div);
 }

 $("#dice").click(function() {
   if (!gameFinished && moveFinished) {
     moveFinished = false;
     prepareAnimation();
     performRequest(function(data) {

       $("#diceImage").attr("src","img/wuerfel"+data.player1DiceResult+".png");
       $("#diceImage").attr("alt","W&uuml;rfel mit einer "+altTextMap[data.player1DiceResult]);
       movePlayer('#player1', 0, data.player1DiceResult, data.player1Position, function() {
         movePlayer('#player2', 1, data.player2DiceResult, data.player2Position, function() {
           if (data.gameFinished == "true")
             finishGame();
           completeAnimation();
           moveFinished = true;
         });
       });
     });
   }
   return false;
 });


//]]>
