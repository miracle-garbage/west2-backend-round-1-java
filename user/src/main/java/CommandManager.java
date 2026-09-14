import data.JsonData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    // 命令注册表
    private static final Map<String,> commandMap = new HashMap<>();
    static {
        commandMap.put();

    }
    private static final ArrayList<String> extensions = new ArrayList<>();
    static {
        extensions.add(".txt");
    }

    // 具体命令
    private final ArrayList<String> args;

    public CommandManager(ArrayList<String> args) {
        this.args = args;
    }

    // 除去第一个元素后的参数列表
    public ArrayList<String> getNextArgs(ArrayList<String> args) {
        return new ArrayList<>(args.subList(1, args.size()));
    }

    // 命令转发 采用递归实现 在input.txt里再次写input2.txt...会栈溢出
    public void commandManager(JsonData data) {
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


    }

}
