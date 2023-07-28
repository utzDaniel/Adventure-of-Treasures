package backend.service;

import java.util.Locale;

public final class EnumValidator {

    private EnumValidator() {
    }

    public static <E extends Enum<E>> boolean isValid(Class<E> enumClass, String value) {
        try {
            Enum.valueOf(enumClass, value.toUpperCase(Locale.ROOT));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static <E extends Enum<E>> Enum<E> getValue(Class<E> enumClass, String value) {
        try {
            return Enum.valueOf(enumClass, value.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
