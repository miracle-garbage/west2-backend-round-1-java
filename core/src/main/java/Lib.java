import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.*;
import data.JsonData;
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


}



class DataPath {
    private int status; // 0 表示用默认目录：与 jar 文件同目录；1 表示使用 path 写入的目录
    private String path; // data.json 文件目录

    // setter getter
    public int getStatus() { return status; }
    public String getPath() { return path; }
}
