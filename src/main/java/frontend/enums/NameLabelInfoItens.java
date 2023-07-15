package frontend.enums;

public enum NameLabelInfoItens {

    CAPICIDADE("Capacidade do inventario %d/%d"), NOME("Nome"), PESO("Peso"), DESCRICAO("Descrição");

    private final String name;

    NameLabelInfoItens(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
