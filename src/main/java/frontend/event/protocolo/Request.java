package frontend.event.protocolo;

import java.util.HashMap;
import java.util.Map;

public final class Request {
    private final String nomeControle;
    private final String nomeMetodo;
    private final Map<String, Object> queryParams;

    public Request(String url) {
        String[] partesUrl = url.replaceFirst("/", "").split("[?]");
        String[] controleEMetodo = partesUrl[0].split("/");
        this.nomeControle = Character.toUpperCase(controleEMetodo[0].charAt(0)) + controleEMetodo[0].substring(1)+ "Controller";
        this.nomeMetodo = controleEMetodo[1];
        this.queryParams = (partesUrl.length > 1 ? (new QueryParamsBuilder()).comParametros(partesUrl[1]).build() : new HashMap<>());
    }

    public String getNomeControle() {
        return this.nomeControle;
    }

    public String getNomeMetodo() {
        return this.nomeMetodo;
    }

    public Map<String, Object> getQueryParams() {
        return this.queryParams;
    }
}
