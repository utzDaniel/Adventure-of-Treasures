package backend.service.interfaces;

public interface IUsable extends IEffect {

    void use();

    String getLocalUse();

    String getName();

    String getEffect();
}