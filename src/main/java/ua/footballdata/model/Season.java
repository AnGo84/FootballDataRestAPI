
package ua.footballdata.model;


public class Season {

    private long id;
    private String startDate;
    private String endDate;
    private Object currentMatchday;
    private Object winner;

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

    public Object getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(Object currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public Object getWinner() {
        return winner;
    }

    public void setWinner(Object winner) {
        this.winner = winner;
    }

}
