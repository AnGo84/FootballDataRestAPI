
package ua.footballdata.model;


public class CurrentSeason {

    private long id;
    private String startDate;
    private String endDate;
    private long currentMatchday;
    private Team winner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(long currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

}
