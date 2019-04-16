package patterns.decorator;

import patterns.entities.Param;
import patterns.entities.Result;

public interface ActionDecorator {

    <R extends Result, P extends Param> R execute(Action<R, P> action, P param) throws Throwable;
}
