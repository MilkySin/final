package com.dreamcatcher.genie.app.core;

import java.util.function.Supplier;

public class App {

    protected static Container container = null;

    public static void setContainer(Container container)
    {
        App.container = container;
    }

    public static Container getContainer()
    {
        return App.container;
    }

    public static void bind(String key, Supplier<?> resolver)
    {
        App.container.bind(key, resolver);
    }

    public static Object resolve(String key) throws Exception {
        return App.container.resolve(key);
    }
}
