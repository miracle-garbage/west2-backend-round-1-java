package user.lib;

import java.util.ArrayList;

public class CommandParam {
    private final ArrayList<String> args;

    public CommandParam(ArrayList<String> args) {
        this.args = args;
    }

    public CommandParam(CommandParam param,int begin,int end) {
        this.args = new ArrayList<String>(param.args.subList(begin, end));
    }

    public CommandParam(CommandParam param,int begin) {
        // 除去第一个元素后的参数列表
        this.args = new ArrayList<String>(param.args.subList(begin, param.args.size()));
    }

    public String getFirstArg() {
        return args.getFirst();
    }

    public String getArg(int index) {
        return args.get(index);
    }

    // 几个参数
    public int size() {
        return args.size();
    }

    public boolean isEmpty() {
        return args.isEmpty();
    }


}
