package dom;

import java.util.HashMap;
import java.util.Map;

public class Player {

    private String username;
    private String gender;
    private Boolean hasPlayed;
    private Integer winnerCount;
    private Integer participatedCount;
    private Map<Integer, Integer> diceStatistics;

    public Player() {
        diceStatistics = new HashMap<Integer, Integer>();
    }

    public Map<Integer, Integer> getDiceStatistics() {
        return diceStatistics;
    }

    public void setDiceStatistics(Map<Integer, Integer> diceStatistics) {
        this.diceStatistics = diceStatistics;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getHasPlayed() {
        return hasPlayed;
    }

    public void setHasPlayed(Boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    public Integer getParticipatedCount() {
        return participatedCount;
    }

    public void setParticipatedCount(Integer participatedCount) {
        this.participatedCount = participatedCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getWinnerCount() {
        return winnerCount;
    }

    public void setWinnerCount(Integer winnerCount) {
        this.winnerCount = winnerCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Player [");
        if (username != null) {
            sb.append("username=").append(username).append(", ");
        }
        if (gender != null) {
            sb.append("gender=").append(gender).append(", ");
        }
        if (hasPlayed != null) {
            sb.append("hasPlayed=").append(hasPlayed).append(", ");
        }
        if (winnerCount != null) {
            sb.append("winnerCount=").append(winnerCount).append(", ");
        }
        if (participatedCount != null) {
            sb.append("participatedCount=").append(participatedCount).append(", ");
        }
        if (diceStatistics != null) {
            sb.append("diceStatistics=").append(diceStatistics);
        }
        sb.append("]");

        return sb.toString();
    }
}
