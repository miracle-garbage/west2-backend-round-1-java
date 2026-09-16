package Lib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

// 缓存已打开文件对象
public class FileCache {
    private final Map<Path, BufferedWriter> cache;

    public FileCache() {
        this.cache = new HashMap<>();
    }

    public void loadFile(String filename) {
        /*
            根据给到的文件名，构造合法的Path添加到cache
            先判断路径是否正确，不正确抛出异常
            路径正确后：
                文件已经存在：
                    直接构造加入cache，append模式
                文件不存在：
                    创建文件并加入cache，append模式

            注意同步访问问题，只判断文件存在性而不访问占用文件

            code is generate with ai ds
         */
        if (filename == null || filename.isBlank()) {
            throw new IllegalArgumentException("文件名不能为空");
        }

        // 构造合法Path：相对路径统一转成绝对路径，并消去 "." 和 ".."
        Path path = Path.of(filename).toAbsolutePath().normalize();

        // 上级目录必须存在，否则路径不正确
        Path parent = path.getParent();
        if (parent == null || !Files.isDirectory(parent)) {
            throw new IllegalArgumentException("上级目录不存在或不是目录：" + path);
        }

        // 同步访问：同一文件只打开一次，并避免并发下重复创建
        // TODO 实际上并没有实现并发运行指令
        synchronized (cache) {
            try {
                // 只判断存在性，不通过打开文件的方式去测试，避免占用文件
                if (Files.exists(path) && !Files.isRegularFile(path)) {
                    throw new IOException("路径已存在但不是普通文件：" + path);
                }
                if (Files.notExists(path)) {
                    Files.createFile(path);
                }

                // 用真实路径做key，相对路径与绝对路径指向同一文件时命中同一条目
                Path key = path.toRealPath();

                // append模式打开，已缓存过则直接复用
                if (!cache.containsKey(key)) {
                    cache.put(key, new BufferedWriter(new FileWriter(key.toFile(), true)));
                }
            } catch (IOException e) {
                throw new UncheckedIOException("打开文件失败：" + path, e);
            }
        }
    }
}
