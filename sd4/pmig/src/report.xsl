<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns="http://www.w3.org/1999/LaTeX"
  xmlns:t="http://www.dbai.tuwien.ac.at/education/ssd/SS13/uebung/Tournament">

  <xsl:output method="text" />
  <xsl:template match="text()"/>

  <xsl:template match="/">
\documentclass{article}
\begin{document}

\title{Tournament Bericht}
\author{Philip Miglinci\\
 1127853\\
 p.miglinci@gmail.com
}
\date{Juni 2013}
\maketitle
    <xsl:apply-templates/>
\begin{end}
  </xsl:template>

  <xsl:template match="t:tournament">
\section{Tournament Info}
  <xsl:value-of select="t:description"/>

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
    \subsubsection*{Spiel Fritz vs Maria (<xsl:value-of select="t:game/@status" />)}
   \begin{enumerate}
    <xsl:apply-templates />
   \end{enumerate}
  </xsl:template>

  <xsl:template match="t:game-history">
    \item Fritz,
      \textbf{[Dots]} 5,
      \textbf{[Start]} 12
      \textbf{[End]} 17
  </xsl:template>

</xsl:stylesheet>
