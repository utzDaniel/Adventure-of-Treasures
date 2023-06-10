package backend.service.interfaces;

public interface IUsable extends IEffect {

    boolean use();

    String getLocalUse();

    String getName();

    String getEffect();
}