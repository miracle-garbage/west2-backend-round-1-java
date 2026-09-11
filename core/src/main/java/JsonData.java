/*
    用于写入json数据的数据结构
    根据数据包结构构建
 */

import java.time.LocalDate;
import java.util.List;

public class JsonData {
    private int schemaVersion;
    private Competition competition;
    private List<Player> players;
    private List<Event> events;

    int getSchemaVersion() {
        return schemaVersion;
    }

    Competition getCompetition() {
        return competition;
    }

    List<Player> getPlayers() {
        return players;
    }

    List<Event> getEvents() {
        return events;
    }
}

class Competition {
    private String id;
    private String name;
    private LocalDate from;
    private LocalDate to;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
}

class Player {
    private String fullName;
    private String gender;
    private String country;
    private String countryCode;

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }
}

class Event {
    private String command;
    private String eventId;
    private String gender;
    private String discipline;
    private String eventType;
    private List<Result> results;

    public void setCommand(String command) {
        this.command = command;
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

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public List<Result> getResults() {
        return results;
    }
}

class Result {
    private String fullName;
    private int rank;
    private String countryCode;
    private List<Double> scores;
    private double totalPoints;

    public String getFullName() {
        return fullName;
    }

    public int getRank() {
        return rank;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public List<Double> getScores() {
        return scores;
    }

    public double getTotalPoints() {
        return totalPoints;
    }
}
