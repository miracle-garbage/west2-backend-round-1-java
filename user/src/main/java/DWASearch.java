import data.JsonData;

import java.util.List;

public class DWASearch {
    public static void main(String[] args) {
        // 读取数据
        Application app = new Application();
        app.loadData();

        // 输出所有
        app.getData().printAll();
    }
}



