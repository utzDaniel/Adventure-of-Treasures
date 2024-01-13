package backend.service.command;

import backend.controller.enums.TypeMessage;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public final class TypeMessageCombination {

    private TypeMessageCombination() {

    }

    public static Optional<TypeMessage> combine(Set<TypeMessage> types) {
        if (Objects.isNull(types) || types.isEmpty()) return Optional.empty();
        if (types.size() == 1) return Optional.of(types.iterator().next());
        var typeErro = getTypeMessageErro(types);
        if (typeErro.isPresent()) return typeErro;
        return getTypeMessage(types.stream().map(Enum::name).sorted().toList());
    }

    private static Optional<TypeMessage> getTypeMessageErro(Set<TypeMessage> types) {
        for (TypeMessage type : types) {
            if (type.name().contains("ERRO")) return Optional.of(type);
        }
        return Optional.empty();
    }

    private static Optional<TypeMessage> getTypeMessage(List<String> names) {
        var nameEnum = getNameEnum(names);
        return selectTypeMessage(nameEnum);
    }

    private static String getNameEnum(List<String> names) {
        StringBuilder nameEnum = new StringBuilder();
        names.forEach(v -> nameEnum.append(v, 0, v.lastIndexOf("_") + 1));
        nameEnum.append(names.get(0), names.get(0).lastIndexOf("_") + 1, names.get(0).length());
        return nameEnum.toString();
    }


    private static Optional<TypeMessage> selectTypeMessage(String nameEnum) {
        try {
            return Optional.of(Enum.valueOf(TypeMessage.class, nameEnum));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
