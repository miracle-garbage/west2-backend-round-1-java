package user.commands;

import user.lib.CommandParam;
import user.lib.FileCache;
import core.data.JsonData;
import user.exceptions.ParamException;

public class Result extends Command {
    public Result() {
        super("result");
    }

    @Override
    public void execute(JsonData data, CommandParam param, FileCache fileCache) throws ParamException {
        // TODO
        ;
    }

    @Override
    public void writeToFile(JsonData data, FileCache fileCache, String filename) {
        // TODO
        ;
    }
}
