package frontend.event.reflexao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public final class ManipuladorMetodo {

    private final Object instancia;
    private final Method metodo;
    private final Map<String, Object> params;
    private BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao;

    public ManipuladorMetodo(Object instancia, Method metodo, Map<String, Object> params) {
        this.instancia = instancia;
        this.metodo = metodo;
        this.params = params;
    }

    public Object invoca() {
        try {
            List<Object> parametros = new ArrayList<>();
            Stream.of(this.metodo.getParameters()).forEach(p ->
                parametros.add(this.params.get(p.getName())));
            return this.metodo.invoke(this.instancia, parametros.toArray());
        } catch (IllegalArgumentException | IllegalAccessException var2) {
            var2.printStackTrace();
            throw new RuntimeException(var2);
        } catch (InvocationTargetException var3) {
            if (this.tratamentoExcecao != null) {
                return this.tratamentoExcecao.apply(this.metodo, var3);
            } else {
                var3.printStackTrace();
                throw new RuntimeException("Erro dentro do método!", var3);
            }
        }
    }

    public ManipuladorMetodo comTratamentoDeExcecao(BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao) {
        this.tratamentoExcecao = tratamentoExcecao;
        return this;
    }
}
