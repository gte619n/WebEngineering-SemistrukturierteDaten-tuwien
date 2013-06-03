(: build file didnt work run :)
(: java -cp lib/saxon9he.jar net.sf.saxon.Query -q:"src/xquery.xq" -o:"output/xquery-output.xml" :)
declare namespace t="http://www.dbai.tuwien.ac.at/education/ssd/SS13/uebung/Tournament";
element champions {
  for $gamewinner in distinct-values(doc('../resources/tournament.xml')//t:game[@winner]/@winner)
  let $wonGames := doc('../resources/tournament.xml')//t:game[@winner = $gamewinner]
  order by count($wonGames) descending
  return (
    <champion name="{$gamewinner}" won-games="{count($wonGames)}">
    {
      for $opponent in $wonGames//t:player[not(@ref=$gamewinner)]
      return <defeat name="{$opponent/@ref}" />
    }
    </champion>
    )
}
