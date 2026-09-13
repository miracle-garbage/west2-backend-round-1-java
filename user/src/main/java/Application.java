import data.JsonData;

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