package user.commands;

import user.lib.CommandParam;
import user.lib.FileCache;
import core.data.JsonData;
import user.exceptions.ParamException;

import java.util.Map;

public abstract class Command {
    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void execute(JsonData data, CommandParam param, FileCache fileCache) throws ParamException;
    public abstract void writeToFile(Object data, FileCache fileCache,String filename);
    public abstract void displayOnTerminal(Object data);

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
