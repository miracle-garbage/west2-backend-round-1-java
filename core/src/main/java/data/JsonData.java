package data;/*
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

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public Competition getCompetition() {
        return competition;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Event> getEvents() {
        return events;
    }
}

