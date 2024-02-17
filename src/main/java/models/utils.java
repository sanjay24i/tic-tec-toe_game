package models;

public class utils {
    public static String makeBold(String str) {
        return "\033[1m" + str + "\033[0m";
    }
}
