package patterns.decorator;

import patterns.entities.ActionStatusEnum;
import patterns.entities.Param;
import patterns.entities.Result;

import java.util.Optional;

public class DefaultActionDecorator implements ActionDecorator{

    private void beforeExecute (Action action, Param param) {
        log("DefaultActionDecorator -> beforeExecute");
    }

    private void afterExecute (Action action, Result result, Param param, ActionStatusEnum status, Optional<Exception> exception) {

        if (exception.isPresent()) {
            log("DefaultActionDecorator -> afterExecute with Exception");
            System.out.println(exception.get().getMessage());
        } else {
            log("DefaultActionDecorator -> afterExecute OK");
        }
    }

    private void log (String msg) {
        System.out.println(msg);
    }

    @Override
    public <R extends Result, P extends Param> R execute(Action<R, P> action, P param) throws Throwable {

        R result = null;

        action.validateParam(param);

        beforeExecute(action, param);

        try {
            result = action.execute(param);
            afterExecute(action, result, param, ActionStatusEnum.ERROR, Optional.empty());
            return result;
        } catch (Exception ex) {
            afterExecute(action, result, param, ActionStatusEnum.ERROR, Optional.of(ex));
            throw ex;
        }
    }
}
