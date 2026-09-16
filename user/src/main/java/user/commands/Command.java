package user.commands;

import user.lib.CommandParam;
import user.lib.FileCache;
import core.data.JsonData;
import user.exceptions.ParamException;

public abstract class Command {
    String name;
    // TODO:指令支持的输出文件格式扩展名表

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void execute(JsonData data, CommandParam param, FileCache fileCache) throws ParamException;
    public abstract void writeToFile(JsonData data, FileCache fileCache,String filename);
}
