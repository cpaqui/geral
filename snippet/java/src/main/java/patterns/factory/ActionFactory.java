package patterns.factory;

import patterns.decorator.Action;
import patterns.entities.Param;
import patterns.entities.Result;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ActionFactory {

    public <R extends Result, P extends Param> Action<R, P> get(Class<? extends Action<R, P>> actionClass) {

        for (Constructor<?> constructor : actionClass.getConstructors()) {
            Class<?>[] parametersTypes = constructor.getParameterTypes();
            Object[] objs = new Object[parametersTypes.length];

            for(int i = 0; i < parametersTypes.length; ++i) {
                Class<?> clazz = parametersTypes[i];

                try {
                    objs[i] = clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            try {
                return (Action<R, P>) constructor.newInstance(objs);
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
        }

        throw new IllegalStateException("Unable to instantiate action");
    }
}
