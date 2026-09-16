package core.lib;

import core.data.JsonData;
import core.data.Player;

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
}
