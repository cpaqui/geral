package patterns.builder;

import patterns.decorator.Action;
import patterns.decorator.ActionDecorator;
import patterns.decorator.DefaultActionDecorator;
import patterns.entities.Param;
import patterns.entities.Result;
import patterns.factory.ActionFactory;

public class CrudAction {

    private ActionFactory actionFactory;
    private ActionDecorator handler;

    public CrudAction() {
        this.actionFactory =  new ActionFactory();
        this.handler = new DefaultActionDecorator();
    }

    public <R extends Result, P extends Param> R execute(Class<? extends Action<R, P>> actionClazz, P param) throws Throwable {
        Action<R, P> action = actionFactory.get(actionClazz);
        return handler.execute(action, param);
    }

}
