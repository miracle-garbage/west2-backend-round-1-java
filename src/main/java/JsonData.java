/*
    用于写入json数据的数据结构
 */

import javax.xml.namespace.QName;
import java.time.LocalDate;
import java.util.List;

class JsonData {
    private int schemaVersion;
    private Competition competition;
    private List<Player> players;
    private List<Event> events;
}

class Competition {
    private String id;
    private String name;
    private LocalDate from;
    private LocalDate to;
}

class Player {
    private String fullName;
    private String gender;
    private String country;
    private String countryCode;
}

class Event {
    private String command;
    private String eventId;
    private String gender;
    private String discipline;
    private String eventType;
    private List<Result> results;
}

class Result {
    private String fullName;
    private int rank;
    private String countryCode;
    private List<Double> scores;
    private double totalPoints;
}
