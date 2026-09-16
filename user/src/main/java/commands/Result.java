package commands;

import Lib.CommandParam;
import data.JsonData;
import exceptions.ParamException;

public class Result extends Command {
    public Result() {
        super("result");
    }

    @Override
    public void execute(JsonData data, CommandParam param) throws ParamException {
        // TODO
        ;
    }
}
