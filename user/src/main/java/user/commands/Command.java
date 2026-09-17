package user.commands;

import core.lib.OutputFormat;
import user.lib.CommandParam;
import user.lib.FileCache;
import core.data.JsonData;
import user.exceptions.ParamException;

import java.io.BufferedWriter;
import java.util.Map;

public abstract class Command {
    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void displayOnTerminal(Object data);
    public abstract void execute(JsonData data, CommandParam param, FileCache fileCache) throws ParamException;

    public void writeToFile(Object data, FileCache fileCache,String filename) {
        if (data == null) {
            BufferedWriter fileWriter = fileCache.createFileWriter(filename);
            // 将输出内容写入文件
            OutputFormat.writeAllPlayersInfo((JsonData) data,fileWriter);
            System.out.printf("文件 %s 写入成功\n",filename);
        } else {
            throw new RuntimeException("读取的数据与预定格式不匹配");
        }
    }

    @FunctionalInterface
    interface Fn {
        void apply(DataPack dataPack);
    }

}

class DataPack {
    public Object data;
    public FileCache fileCache;

    public String arg;

    public DataPack(Object data, FileCache fileCache,String arg) {
        this.data = data;
        this.fileCache = fileCache;
        this.arg = arg;
    }
}
