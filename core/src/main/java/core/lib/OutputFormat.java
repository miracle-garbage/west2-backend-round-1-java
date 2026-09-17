package core.lib;

import core.data.Event;
import core.data.JsonData;
import core.data.Player;
import core.data.Result;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

public class OutputFormat {
    public static String outputPlayerFormat(Player player) {
        return
            DataOutput.outputPlayerName(player) +
            DataOutput.outputPlayerGender(player) +
            DataOutput.outputPlayerCountry(player) +
            DataOutput.outputLine();
    }

    // 输出全部选手信息
    public static void displayAllPlayersInfo(JsonData data) {
        for (Player player : data.getPlayers()) {
            System.out.printf("%s",outputPlayerFormat(player));
        }
    }

    // 输出全部选手信息到文件
    public static void writeAllPlayersInfo(JsonData data, BufferedWriter writer) {
        try {
            for (Player player : data.getPlayers()) {
                writer.write(outputPlayerFormat(player));
            }
        } catch (IOException e) {
            throw new UncheckedIOException("写入文件失败",e);
        }
    }

    // 输出比赛结果
    public static String outputResultPlayerFormat(Result r) {
        return DataOutput.outputResultPlayerName(r) +
                DataOutput.outputResultPlayerRank(r) +
                DataOutput.outputResultPlayerScore(r) +
                DataOutput.outputLine();
    }

    public static void displayEventResult(Event e) {
        for (Result r : e.getResults()) {
            System.out.printf("%s",outputResultPlayerFormat(r));
        }
    }

    public static void writeResultPlayerFormat(Event e,BufferedWriter writer) {
        try {
            for (Result r : e.getResults()) {
                writer.write(outputResultPlayerFormat(r));
            }
        } catch (IOException ex) {
            throw new UncheckedIOException("写入文件失败",ex);
        }
    }
}
