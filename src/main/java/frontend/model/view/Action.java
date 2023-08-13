package frontend.model.view;

import backend.controller.interfaces.IActionResponse;
import backend.controller.interfaces.IComponentInfo;

import java.util.List;

public record Action(List<IComponentInfo> components, String song) implements IActionResponse {

}
