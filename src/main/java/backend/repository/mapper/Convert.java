package backend.repository.mapper;

public final class Convert {

    private Convert(){}

    public static boolean parseBoolean(String dado) {
        var value = dado.trim();
        return value.equals("true");
    }

    public static int parseInt(String dado) {
        var value = dado.trim();
        return value.equals("null") ? -1 : Integer.parseInt(value);
    }
    public static String parseString(String dado) {
        var value = dado.trim();
        return value.equals("null") ? null : value;
    }

}
