import commands.Command;
import commands.CommandParam;
import commands.Players;
import data.JsonData;
import exceptions.ParamException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    // 命令注册表
    private static final Map<String, Command> commandMap = new HashMap<>();
    static {
        Players players = new Players();
        commandMap.put(players.getName(),players);
    }
    private static final ArrayList<String> extensions = new ArrayList<>();
    static {
        extensions.add(".txt");
    }

    // 具体命令
    private final CommandParam args;

    public CommandManager(ArrayList<String> args) {
        this.args = new CommandParam(args);
    }

    // 命令转发 只检查第一个参数
    public void commandManager(JsonData data) throws ParamException {
        /*
            TODO
            维护一个指令队列。
            先根据第一个参数判断是指令还是文件（且判断合法性，非法参数直接输出Error）
            如果是文件：
                尝试读取文件里的所有指令并依次添加到指令队列里
            如果是指令：
                将这个指令添加到指令队列即可

            最后：根据队列迭代执行所有指令
         */

        // 指令队列：每个元素是一条指令的参数列表
        ArrayDeque<CommandParam> commandQueue = new ArrayDeque<>();

        if (args.isEmpty()) {
            throw new ParamException("无任何参数输入");
        }

        if (isLegal(args.getFirstArg())) {
            if (isCommand(args.getFirstArg())) {
                commandQueue.addLast(args);
            } else {
                // 读取文件，将里面的内容每行为一个命令读取构造依次加入队列,文件不存在抛出异常即可
                try {
                    for (String line : Files.readAllLines(Path.of(args.getFirstArg()))) {
                        // 跳过空行
                        if (line.isBlank()) {
                            continue;
                        }
                        // 一行按空白切分成多个参数，构造为一条指令加入队列末尾
                        commandQueue.addLast(new CommandParam(new ArrayList<>(List.of(line.trim().split("\\s+")))));
                    }
                } catch (IOException e) {
                    // 文件不存在等读取失败的情况直接抛出
                    throw new UncheckedIOException(e);
                }
            }
        } else {
            throw new ParamException("首个参数错误");
        }

        // 迭代执行指令，每个都进行合法检查
        for (CommandParam argLine : commandQueue) {
            try {
                if (!isCommand(argLine.getFirstArg()) || !isLegal(argLine.getFirstArg())) {
                    throw new ParamException("首个参数错误");
                } else {
                    // 指令转发
                    commandMap.get(argLine.getFirstArg()).execute(data,new CommandParam(argLine,1));
                }
            } catch (ParamException ex) {
                System.out.println("Error");
            }
        }
    }

    // 判断首个参数的合法性
    public boolean isLegal(String arg) {
        if (arg == null || arg.isEmpty()) {
            return false;
        }
        // 如果是文件查看扩展名是否在extensions中
        if (isFile(arg)) {
            int dotIndex = arg.lastIndexOf('.');
            String extension = arg.substring(dotIndex);
            return extensions.contains(extension);
        }
        // 如果是命令查看是否在commandMap的key中
        return commandMap.containsKey(arg);
    }

    // 判断是否是文件
    public boolean isFile(String arg) {
        return !isCommand(arg) && arg.charAt(0) != '.';
    }

    // 判断是否是命令
    public boolean isCommand(String arg) {
        // 只看有没有"."
        return !arg.contains(".");
    }
}
