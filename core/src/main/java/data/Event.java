package data;

import java.util.List;

public class Event {
    private String command;
    private String eventId;
    private String gender;
    private String discipline;
    private String eventType;
    private List<Result> results;

    public String getCommand() {
        return command;
    }

    public String getEventId() {
        return eventId;
    }

    public String getGender() {
        return gender;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getEventType() {
        return eventType;
    }

    public List<Result> getResults() {
        return results;
    }
}
