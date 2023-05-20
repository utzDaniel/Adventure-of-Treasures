package frontend.settings;

public final class SettingsSong {

    private static boolean onPlayer = false;

    public static boolean isOnPlayer() {
        return onPlayer;
    }

    public static void setOnPlayer() {
        SettingsSong.onPlayer = !isOnPlayer();
    }

}
