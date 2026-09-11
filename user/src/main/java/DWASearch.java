import java.util.List;

public class DWASearch {
    public static void main(String[] args) {

    }
}

class Command {
    // 命令行解析
    private final List<String> args;

    public Command (List<String> args) {
        this.args = List.copyOf(args);
    }


}

class Application {
    // 初始化程序
    private final JsonData data;

    public Application() {
        this.data = new JsonData();
    }

    public void loadData() {

    }
}