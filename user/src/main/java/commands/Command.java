package commands;

import Lib.CommandParam;
import data.JsonData;
import exceptions.ParamException;

public abstract class Command {
    String name;
    // TODO:指令支持的输出文件格式扩展名表

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void execute(JsonData data, CommandParam param) throws ParamException;
}
