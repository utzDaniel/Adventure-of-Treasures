package frontend.event.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Stream;

public final class ContainerIoC {
    private final Map<Class<?>, Class<?>> mapaDeTipos = new HashMap<>();

    public Object getInstancia(Class<?> tipoFonte) {
        Class<?> tipoDestino = this.mapaDeTipos.get(tipoFonte);
        if (tipoDestino != null) {
            return this.getInstancia(tipoDestino);
        } else {
            Stream<Constructor<?>> construtores = Stream.of(tipoFonte.getDeclaredConstructors());
            Optional<Constructor<?>> construtorPadrao = construtores.filter(construtorx ->
                construtorx.getParameterCount() == 0
            ).findFirst();

            try {
                if (construtorPadrao.isPresent()) {
                    return construtorPadrao.get().newInstance();
                } else {
                    Constructor<?> construtor = tipoFonte.getDeclaredConstructors()[0];
                    List<Object> params = new ArrayList<>();
                    Parameter[] var10 = construtor.getParameters();

                    for (Parameter param : var10) {
                        Class<?> tipoDoParametro = param.getType();
                        params.add(this.getInstancia(tipoDoParametro));
                    }

                    return construtor.newInstance(params.toArray());
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException var12) {
                throw new RuntimeException(var12);
            }
        }
    }

    public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {
        this.mapaDeTipos.put(tipoFonte, tipoDestino);
    }
}