package core.lib;

import core.data.Player;

public class DataOutput {
    // 一些输出格式约束 均有换行
    public static String outputLine(int len) {
        return "-".repeat(len)+"\n";
    }

    public static String outputLine() {
        return outputLine(5);
    }

    public static String outputPlayerName(Player p) {
        String t = p.getFullName();
        return "Full Name:"+t+"\n";
    }

    public static String outputPlayerGender(Player p) {
        String t = p.getGender();
        return "Gender:"+t+"\n";
    }

    public static String outputPlayerCountry(Player p) {
        String t = p.getCountry();
        return "Country:"+t+"\n";
    }



}



