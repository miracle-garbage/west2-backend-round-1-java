package commands;

import data.JsonData;
import exceptions.ParamException;
import lib.Lib;

public class Players extends Command {
    public Players() {
        super("players");
    }

    @Override
    public void execute(JsonData data, CommandParam param) {
        // 文件名或为空
        if (param.paramSize() > 1) throw new ParamException("players参数错误：参数过多");
        else if (param.paramSize() == 1) {
            // TODO 写文件
            ;
        }
        else {
            Lib.displayAllPlayerInfo(data);
        }
    }
}
