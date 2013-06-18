<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns="http://www.w3.org/1999/LaTeX"
  xmlns:t="http://www.dbai.tuwien.ac.at/education/ssd/SS13/uebung/Tournament">

  <xsl:output method="text" />
  <xsl:template match="text()"/>

  <xsl:param name='name'>Philip Miglinci</xsl:param>
  <xsl:param name='studentid'>1127853</xsl:param>
  <xsl:param name='email'>p.miglinci@gmail.com</xsl:param>

  <xsl:template match="/">
\documentclass{article}
\begin{document}

\title{Tournament Bericht}
\author{<xsl:value-of select="$name" />\\
 <xsl:value-of select="$studentid" />\\
 <xsl:value-of select="$email" />
}
\date{Juni 2013}
\maketitle
    <xsl:apply-templates/>
\end{document}
  </xsl:template>

  <xsl:template match="t:tournament">
\section{Tournament Info}
  <xsl:value-of select="normalize-space(t:description)"/>

\begin{description}
 \item[Start] <xsl:value-of select="@start-date" />
 \item[Ende] <xsl:value-of select="@end-date" />
 \item[Deadline] <xsl:value-of select="@registration-deadline" />
\end{description}
    <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="t:tournament/t:players">
\section{Spieler}

\begin{center}
  \begin{table}[h]
      \begin{tabular}{lll}
        \textbf{Username} &amp; \textbf{Geschlecht} &amp; \textbf{Geburtsdatum} \\
        \hline
    <xsl:apply-templates />
      \end{tabular}
  \end{table}
\end{center}
  </xsl:template>

  <xsl:template match="//t:tournament/t:players/t:player">
    <xsl:value-of select="@username" /> &amp; <xsl:value-of select="t:gender" /> &amp; <xsl:value-of select="t:date-of-birth" /> \\
  </xsl:template>

  <xsl:template match="t:rounds">
    \section{Runden}
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="t:round">
    \subsection{Runde <xsl:value-of select="@number" />}
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="t:game">
    \subsubsection*{Spiel <xsl:value-of select="t:players/t:player[1]/@ref" /><xsl:if test="t:players/t:player[2]/@ref"> vs <xsl:value-of select="t:players/t:player[2]/@ref" /></xsl:if> (<xsl:value-of select="@status" />)}
    <xsl:choose>
      <xsl:when test="t:game-history">
  \begin{enumerate}
        <xsl:apply-templates />
  \end{enumerate}
      </xsl:when>
      <xsl:otherwise>
        Keine Spiel History vorhanden.
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template match="t:game-history/t:move">
    \item <xsl:value-of select="@player" />,
      \textbf{[Dots]} <xsl:value-of select="@dots" />,
      \textbf{[Start]} <xsl:value-of select="@start-point" />
      \textbf{[End]} <xsl:value-of select="@end-point" />
  </xsl:template>

</xsl:stylesheet>
