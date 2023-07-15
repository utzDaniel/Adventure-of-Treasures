package frontend.enums;

public enum NameJMenu {

    HISTORIA("Historia"),
    COMANDOS("Comandos"),
    AJUDA("Ajuda"),
    MUSICA("Musica"),
    EFEITOS("Efeitos"),
    SAIR("Sair");

    private final String name;

    NameJMenu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
