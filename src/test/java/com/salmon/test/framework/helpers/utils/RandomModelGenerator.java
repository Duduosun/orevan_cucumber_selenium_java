package com.salmon.test.framework.helpers.utils;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.lang.reflect.Type;

//Use RandomModelData interface and not this class implementation

public abstract class RandomModelGenerator {

    /**
     * Returns an instance of the given class filled with
     * random data values
     *
     * @param modelClass The name of the Model class for which random Data needs to be pre-populated
     *                   is required
     * @return An instance of &lt;T&gt; filled with dummy values
     */


    public static <T> T randomModelData(Class<T> modelClass, Type... genericTypeArgs) {
        PodamFactory podamFactory;
        podamFactory = new PodamFactoryImpl();
        return (podamFactory.manufacturePojo(modelClass));
    }


}
