package settings;

public class SettingsSong {

    private static boolean onPlayer = false;

    public static boolean isOnPlayer() {
        return onPlayer;
    }

    public static void setOnPlayer() {
        SettingsSong.onPlayer = !isOnPlayer();
    }

}
