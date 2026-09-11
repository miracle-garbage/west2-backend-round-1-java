import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.*;

public class Lib {
    public static Path getDataPath() throws RuntimeException {
        // 获取读取data.json文件的路径：同目录 or 自定义
        ObjectMapper mapper = new ObjectMapper();
        DataPath dataPath = new DataPath();
        try (InputStream inputStream = Lib.class.getResourceAsStream("/data_path.json")) {
            if (inputStream == null) System.out.println("无法读取data_path.json");
            else {
                dataPath = mapper.readValue(inputStream,DataPath.class);
            }
        } catch (IOException e) {
            System.out.println("无法读取data_path.json");
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

                return location.getParent();
            } catch (Exception e) {
                throw new RuntimeException("构建path错误");
            }
        } else {
            // user-define
            return Path.of(dataPath.getPath());
        }
    }

    public static JsonData readData() {
        /*
            从数据包读取数据到数据结构并返回
        */
        ObjectMapper mapper = new ObjectMapper();

        try {
            Path path = getDataPath();
            return mapper.readValue(path.toFile(),JsonData.class);
        } catch (Exception e) {
            System.out.println("路径构建错误");
        }

        return null;
    }
}

class DataPath {
    private int status; // 0 表示用默认目录：与 jar 文件同目录；1 表示使用 path 写入的目录
    private String path; // data.json 文件目录

    // setter getter
    public int getStatus() { return status; }
    public String getPath() { return path; }
}
