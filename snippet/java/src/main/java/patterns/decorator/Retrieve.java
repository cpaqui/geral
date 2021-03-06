package patterns.decorator;

import patterns.entities.Param;
import patterns.entities.Result;
import patterns.template.ActionTemplate;

public class Retrieve extends  ActionTemplate<Result<String>, Param<String>> {

    @Override
    public void doExecute(Result result, Param param) {

        result.setData("CRUD Action: Retrieve");
    }

}
