import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.*;
import data.JsonData;
import data.Player;
import exceptions.DataPathException;
import exceptions.PathResolveException;
import exceptions.ReadJsonException;

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
    public static void printLine(int len) {
        System.out.println("-".repeat(len));
    }

    public static void printLine() {
        printLine(5);
    }

    public static void printName(String name) {
        System.out.printf("Full Name:%s\n",name);
    }

    public static void printGender(String gender) {
        System.out.printf("Full Name:%s\n",gender);
    }

    public static void printCountry(String country) {
        System.out.printf("Full Name:%s\n",country);
    }

    public static void printPlayerFormat(Player player) {
        printName(player.getFullName());
        printGender(player.getGender());
        printCountry(player.getCountry());
        printLine();
    }

    // 输出信息
    public static void displayAllPlayerInfo(JsonData data) {
        for (Player player : data.getPlayers()) {
            printPlayerFormat(player);
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
