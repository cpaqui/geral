package patterns.template;

import patterns.decorator.Action;
import patterns.entities.Param;
import patterns.entities.Result;

public abstract class ActionTemplate <R extends Result, P extends Param> implements Action <Result, Param> {

    public void doValidateParam(Param param) {}

    public abstract void doExecute(Result result, Param param);

    @Override
    public void validateParam(Param param) throws Throwable {
        param.getData().orElseThrow(() -> new RuntimeException("Data param can not be null"));

        doValidateParam(param);
    }

    @Override
    public Result execute(Param param) throws Throwable {

        Result result = new Result();

        try {
            doExecute(result, param);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw  e;
        } finally {
            log("Finally call");
        }
    }

    protected void log (String msg) {
        System.out.println(msg);
    }


}
