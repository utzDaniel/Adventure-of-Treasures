package backend.controller.interfaces;

import java.util.List;

public interface IActionResponse {

    List<IComponentInfo> components();

    String song();

}
