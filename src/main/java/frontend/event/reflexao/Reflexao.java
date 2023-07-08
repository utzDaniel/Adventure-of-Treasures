package frontend.event.reflexao;

public final class Reflexao {

    public Class<?> getClasse(String fqn) {
        try {
            return Class.forName(fqn);
        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
            throw new RuntimeException(var3);
        }
    }
}
