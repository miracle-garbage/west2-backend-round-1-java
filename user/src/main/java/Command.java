import java.util.List;

public class Command {
    // 命令行解析
    private final List<String> args;

    public Command (List<String> args) {
        this.args = List.copyOf(args);
    }


}