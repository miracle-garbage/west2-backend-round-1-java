package app;

import data.JsonData;
import lib.Lib;

public class Application {
    // 初始化程序
    private JsonData data;

    public Application() {
        this.data = new JsonData();
    }

    public void loadData() {
        this.data = Lib.readData();
    }

    public JsonData getData() {
        return data;
    }
}