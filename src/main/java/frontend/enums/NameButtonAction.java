package frontend.enums;

public enum NameButtonAction {

    //TODO remover cancelar e confirmar
    USAR("usar"),
    EQUIPAR("equipar"),
    COMBINAR("combinar"),
    REMOVER("remover"),
    CANCELAR("cancelar"),
    CONFIRMAR("confirmar");

    private final String name;

    NameButtonAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
