import exceptions.ParamException;
import exceptions.ReadJsonException;

import java.util.ArrayList;
import java.util.List;

public class DWASearch {
    public static void main(String[] args) {
        try {
            // 读取数据
            Application app = new Application();
            app.loadData();

            ArrayList<String> argList = new ArrayList<>(List.of(args));
            CommandManager cmd = new CommandManager(argList);

            cmd.commandManager(app.getData());

        } catch (ReadJsonException ex) {
            System.out.println(ex.getMessage()+"，请检查文件是否存在以及路径是否正确");
        } catch (ParamException ex) {
            System.out.println("Error");
        }
    }
}



