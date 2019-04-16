package patterns.facade;

import patterns.builder.CrudAction;
import patterns.decorator.Create;
import patterns.decorator.Delete;
import patterns.decorator.Retrieve;
import patterns.decorator.Update;
import patterns.entities.Param;
import patterns.entities.Result;

public class CrudFacade {

    private CrudAction action;

    public CrudFacade() {
        this.action = new CrudAction();
    }

    public Result create (Param param) throws Throwable {
        return action.execute(Create.class, param);
    }

    public Result retreave (Param param) throws Throwable {
        return action.execute(Retrieve.class, param);
    }

    public Result update (Param param) throws Throwable {
        return action.execute(Update.class, param);
    }

    public Result delete (Param param) throws Throwable {
        return action.execute(Delete.class, param);
    }
}
