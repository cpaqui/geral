package patterns;

import patterns.entities.Param;
import patterns.facade.CrudFacade;

import java.util.Optional;

public class Main {

    public static void main(String[] args) throws Throwable {

        CrudFacade facade = new CrudFacade();

        System.out.println(facade.create(new Param(Optional.of("CREATE"))).getData());
        System.out.println(facade.retreave(new Param(Optional.of("RETRIEVE"))).getData());
        System.out.println(facade.update(new Param(Optional.of("UPDATE"))).getData());
        System.out.println(facade.delete(new Param(Optional.of("DELETE"))).getData());
    }
}
