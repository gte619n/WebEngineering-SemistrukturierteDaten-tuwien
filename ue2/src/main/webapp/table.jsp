<jsp:useBean id="player1" scope ="session" class="Beans.Player" />
<jsp:useBean id="player2" scope ="session" class="Beans.Player" />
<jsp:useBean id="game" scope ="session" class="Beans.Game" />

<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="de" xml:lang="de">
<head>
  <title xml:lang="de">Formel 0 - Spielen</title>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <link rel="stylesheet" type="text/css" href="styles/screen.css" />
  <script src="js/jquery.js" type="text/javascript"></script>
</head>
<body>
  <div id="container">
    <div id="bordercontainer">
      <div id="header">
        <div id="header_left"><h1 class="accessibility">Formel 0</h1></div>
        <div id="header_right"></div>
      </div>
      <div id="navigation">
        <h2 class="accessibility">Navigation</h2>
        <ul title="Navigation">
          <li><a id="startNewGame" href="table" tabindex="1">Neues Spiel</a></li>
          <li><a href="#" tabindex="2">Ausloggen</a></li>
        </ul>
      </div>
      <div id="main-area">
        <div class="info">
          <h2>Spielinformationen</h2>
          <table summary="Diese Tabelle zeigt Informationen zum aktuellen Spiel">
            <tr><th id="leaderLabel" class="label">F&uuml;hrender</th><td id="leader" class="data">mehrere</td></tr>
            <tr><th id="roundLabel" class="label">Runde</th><td id="round" class="data">0</td></tr>
            <tr><th id="timeLabel" class="label">Zeit</th><td id="time" class="data">00:00</td></tr>
            <tr><th id="computerScoreLabel" class="label">W&uuml;rfelergebnis <em><%=player2.getPlayerName()%></em></th><td id="computerScore" class="data">0</td></tr>
          </table>  
          <h2>Spieler</h2>
          <table summary="Diese Tabelle listet die Namen der Spieler auf">
            <tr><th id="player1NameLabel" class="label">Spieler 1</th><td id="player1Name" class="data"><%=player1.getPlayerName()%></td></tr>
            <tr><th id="player2NameLabel" class="label">Spieler 2</th><td id="player2Name" class="data"><%=player2.getPlayerName()%></td></tr>
          </table>    	  
        </div>
        <div class="field">
          <h2 class="accessibility">Spielbereich</h2>
          <ol id="road">
            <li id="start_road">
              <span class="accessibility">Startfeld</span>
              <span id="player1">
                <span class="accessibility"><em>Spieler 1</em></span>
              </span>
              <span id="player2">
                <span class="accessibility"><em>Spieler 2</em></span>
              </span>
            </li>
            <li class="empty_road" id="road_1">
              <span class="accessibility">Feld 2</span>
            </li>
            <li class="oil_road" id="road_2">
              <span class="accessibility">Feld 3</span>
            </li>
            <li class="empty_road" id="road_3">
              <span class="accessibility">Feld 4</span>
            </li>
            <li class="empty_road" id="road_4">
              <span class="accessibility">Feld 5</span>
            </li>
            <li class="oil_road" id="road_5">
              <span class="accessibility">Feld 6</span>
            </li>
            <li id="finish_road">
              <span class="accessibility">Zielfeld</span>
            </li>
          </ol>
        </div>
        <div class="player">
          <h2 class="accessibility">W&uuml;rfelbereich</h2>
          <span class="accessibility">An der Reihe ist</span><div id="currentPlayerName"><%=player1.getPlayerName()%></div>
          <a id="dice" href="#" tabindex="4">
            <img id="diceImage" src="img/wuerfel0.png" alt="W&uuml;rfel mit einer null" />	
          </a>
        </div>
      </div>
    </div>
    <div id="footer">
      &copy; 2013 Formel 0
    </div>
  </div>
  <script type="text/javascript">
    //<![CDATA[
      
      /**
       * How to:
       *
       * 1.) on docuemt load initilize
       * 2.) onclick http post request
       * 3.) modify DOM
       *
       * Modify:
       * 1.) Serverseitig Session handeln
       * 2.) neues spiel macht einen reset
       */
    
    var positionMap =  {"0" : "#start_road", "1" : "#road_1", "2" : "#road_2", "3" : "#road_3", "4" : "#road_4", "5" : "#road_5", "6" : "#finish_road"};
    var altTextMap =  {"0" : "null", "1" : "eins", "2" : "zwei", "3" : "drei"};
    
    var currentPlayerPosition = [0,0];
    var gameFinished = false;
    var moveFinished = true;
      
    function performRequest(callback) {
      $.post(window.location.href, function(data) {
        console.log(data);
        callback(data);
      });
    }

    function movePlayer(playerIdString, playerId, diceResult, finalPosition, callback) {
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
    }

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
          
          $("#leader").html(data.gameLeader);
          $("#time").html(data.gameTime);
          $("#round").html(data.gameRound);
          $("#computerScore").html(data.player2DiceResult);
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
  </script>

  </body>
</html>