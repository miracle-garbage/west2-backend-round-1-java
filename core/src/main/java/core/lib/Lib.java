package core.lib;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.*;
import core.data.JsonData;
import core.data.Player;
import core.exceptions.DataPathException;
import core.exceptions.PathResolveException;
import core.exceptions.ReadJsonException;

public class Lib {
    // 获取数据包路径
    public static Path getDataPath() {
        ObjectMapper mapper = new ObjectMapper();
        DataPath dataPath = new DataPath();

        try (InputStream inputStream = Lib.class.getResourceAsStream("/data_path.json")) {
            if (inputStream == null) throw new DataPathException("无法读取文件");
            else {
                dataPath = mapper.readValue(inputStream,DataPath.class);
            }
        } catch (IOException ex) {
            throw new DataPathException("无法读取文件");
        }

        // 构建Path对象并返回
        if (dataPath.getStatus() == 0) {
            // default
            try {
                Path location = Paths.get(
                        Lib.class
                        .getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .toURI()
                );
                return location.getParent().resolve("data.json");
            } catch (URISyntaxException ex) {
                throw new PathResolveException("无法读取文件");
            }
        } else {
            // user-define
            return Path.of(dataPath.getPath());
        }
    }

    // 读取到数据结构并返回
    public static JsonData readData() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Path path = getDataPath();
            return mapper.readValue(path.toFile(),JsonData.class);
        } catch (IOException ex) {
            throw new ReadJsonException("无法读取文件");
        }
    }

    // 一些输出格式约束 均有换行
    public static String outputLine(int len) {
        return "-".repeat(len)+"\n";
    }

    public static String outputLine() {
        return outputLine(5);
    }

    public static String outputName(String name) {
        return "Full Name:"+name+"\n";
    }

    public static String outputGender(String gender) {
        return "Gender:"+gender+"\n";
    }

    public static String outputCountry(String country) {
        return "Country:"+country+"\n";
    }

    public static String outputPlayerFormat(Player player) {
        return
            outputName(player.getFullName()) +
            outputGender(player.getGender()) +
            outputCountry(player.getCountry()) +
            outputLine();
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



class DataPath {
    private int status; // 0 表示用默认目录：与 jar 文件同目录；1 表示使用 path 写入的目录
    private String path; // data.json 文件目录

    // setter getter
    public int getStatus() { return status; }
    public String getPath() { return path; }
}
