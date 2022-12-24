package settings;

public class SettingsSoundEffects {

    private static boolean onPlayer = true;

    public static boolean isOnPlayer() {
        return onPlayer;
    }

    public static void setOnPlayer() {
        SettingsSoundEffects.onPlayer = !isOnPlayer();
    }

}
