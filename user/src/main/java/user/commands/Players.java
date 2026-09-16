package user.commands;

import core.data.JsonData;
import core.lib.OutputFormat;
import user.exceptions.ParamException;
import user.lib.FileCache;
import user.lib.CommandParam;

import java.io.BufferedWriter;

public class Players extends Command {
    public Players() {
        super("players");
    }

    @Override
    public void execute(JsonData data, CommandParam param, FileCache fileCache) {
        // players [-f filename]

        if (param.size() > 2) throw new ParamException("players参数错误：参数过多");
        if (param.size() == 1) throw new ParamException("参数错误");
        if (param.size() == 2) {
            // 写文件
            String filename = param.getArg(1);
            this.writeToFile(data,fileCache,filename);
        }
        else { // size == 0
            OutputFormat.displayAllPlayersInfo(data);
        }
    }

    @Override
    public void writeToFile(JsonData data, FileCache fileCache, String filename) {
        BufferedWriter fileWriter = fileCache.createFileWriter(filename);
        // 将输出内容写入文件
        OutputFormat.writeAllPlayersInfo(data,fileWriter);
    }
}
