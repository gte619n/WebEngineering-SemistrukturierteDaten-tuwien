<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions"
                xmlns:t="http://www.dbai.tuwien.ac.at/education/ssd/SS13/uebung/Tournament"
                xmlns:saxon="http://saxon.sf.net/">

  <xsl:include href="../resources/util.xsl"/>

  <xsl:output method="html" encoding="UTF-8" indent="yes" omit-xml-declaration="yes" />

  <xsl:template match="/">
    <xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
    <html lang="en">
      <head>
        <meta charset="utf-8"></meta>
        <title>Semistrukturierte Daten - SS13</title>
        <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.1/css/bootstrap-combined.min.css" rel="stylesheet"></link>
      </head>
      <body>
        <div class="container">
				
          <div class="page-header">
            <h1>SSD SS13 - XPath</h1>
          </div>
				
          <xsl:call-template name="shownodes">
            <xsl:with-param name="title" select="'Bis wann darf man sich zu dem Turnier anmelden?'" />
            <xsl:with-param name="query" select="'TODO: XPath Ausdruck hier einfuegen'" />
          </xsl:call-template>				

          <xsl:call-template name="shownodes">
            <xsl:with-param name="title" select="'Wie oft hat Fritz eine Fünf gewürfelt?'" />
            <xsl:with-param name="query" select="'TODO: XPath Ausdruck hier einfuegen'" />
          </xsl:call-template>						
				
          <xsl:call-template name="shownodes">
            <xsl:with-param name="title" select="'Selektieren Sie alle Knoten aus dem Namespace http://www.dbai.tuwien.ac.at/education/ssd/SS13/uebung/Tournament. Beachten Sie dabei, dass der Präfix für einen Namespace nicht immer gleich lauten muss.'" />
            <xsl:with-param name="query" select="'TODO: XPath Ausdruck hier einfuegen'" />
          </xsl:call-template>										
				
          <xsl:call-template name="shownodes">
            <xsl:with-param name="title" select="'Geben Sie das vierte move Element aus, sofern es von Fritz gewürfelt wurde.'" />
            <xsl:with-param name="query" select="'TODO: XPath Ausdruck hier einfuegen'" />
          </xsl:call-template>				
				
          <xsl:call-template name="shownodes">
            <xsl:with-param name="title" select="'Geben Sie das vierte von Fritz gewürfelte move Element aus.'" />
            <xsl:with-param name="query" select="'TODO: XPath Ausdruck hier einfuegen'" />
          </xsl:call-template>	
				
          <xsl:call-template name="shownodes">
            <xsl:with-param name="title" select="'Hat der Turnierteilnehmer Uwe auch tatsächlich ein Spiel gespielt? Die Query soll entweder true oder false zurückliefern.'" />
            <xsl:with-param name="query" select="'TODO: XPath Ausdruck hier einfuegen'" />
          </xsl:call-template>							
				
          <xsl:call-template name="shownodes">
            <xsl:with-param name="title" select="'Geben Sie den Namen jener Spieler aus, welche sich zwar für das Turnier registriert haben, aber an keinem Spiel teilgenommen haben.'" />
            <xsl:with-param name="query" select="'TODO: XPath Ausdruck hier einfuegen'" />
          </xsl:call-template>
				
          <xsl:call-template name="shownodes">
            <xsl:with-param name="title" select="'Ermitteln Sie den Durchschnitt der Würfelergebnisse aller von Fritz getätigten Zügen aus jenen Spielen, an denen sowohl Fritz selbst als auch Maria teilgenommen haben.'" />
            <xsl:with-param name="query" select="'TODO: XPath Ausdruck hier einfuegen'" />
          </xsl:call-template>				

          <xsl:call-template name="shownodes">
            <xsl:with-param name="title" select="'Geben Sie den gesamten Textinhalt des description Elements als (lesbaren) String aus und entfernen Sie eventuell vorhandenen Whitespace.'" />
            <xsl:with-param name="query" select="'TODO: XPath Ausdruck hier einfuegen'" />
          </xsl:call-template>								
				
          <xsl:call-template name="shownodes">
            <xsl:with-param name="title" select="'Jedes XML-Dokument kann bekanntlich als Baum dargestellt werden. Geben Sie die maximale Tiefe des Dokuments aus, d.h. die maximale Länge zwischen Wurzelknoten und beliebigem Blattelement. Hinweis: Werfen Sie einen Blick auf die Achse ancestor.'" />
            <xsl:with-param name="query" select="'TODO: XPath Ausdruck hier einfuegen'" />
          </xsl:call-template>								
        </div>
      </body>
    </html>             
  </xsl:template>


</xsl:stylesheet>
