package backend.service.command;

import backend.controller.enums.TypeMessage;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        var initName = getInitName(names);
        var lastName = getLastName(names);
        Optional<TypeMessage> rsp = Optional.empty();
        for (String name : lastName) {
            rsp = selectTypeMessage(String.format("%s%s", initName, name));
            if (rsp.isPresent()) break;
        }
        return rsp;
    }

    private static String getInitName(List<String> names) {
        StringBuilder nameEnum = new StringBuilder();
        names.forEach(v -> nameEnum.append(v, 0, v.lastIndexOf("_") + 1));
        return nameEnum.toString();
    }

    private static Set<String> getLastName(List<String> names) {
        return names.stream()
                .map(v -> v.substring(v.lastIndexOf("_") + 1))
                .collect(Collectors.toSet());
    }


    private static Optional<TypeMessage> selectTypeMessage(String nameEnum) {
        try {
            return Optional.of(Enum.valueOf(TypeMessage.class, nameEnum));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
