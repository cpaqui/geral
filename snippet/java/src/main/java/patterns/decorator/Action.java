package patterns.decorator;

import patterns.entities.Param;
import patterns.entities.Result;

public interface Action<R extends Result, P extends Param> {

    void validateParam (P param) throws Throwable;

    R execute (P param) throws Throwable;
}