package com.genie.application.core;

import java.util.HashMap;
import java.util.function.Supplier;

/*
    Do NOT use this Ioc Service Container in a real application.
*/

public class Container {
    protected HashMap<String, Supplier<?>> bindings = new HashMap<>();

    public Container() {
    }

    public void bind(String key, Supplier<?> resolver) {
        this.bindings.put(key, resolver);
    }

    public <T> T resolve(String key, Class<T> type) throws Exception {
        if (this.bindings.get(key) == null) {
            throw new Exception("No matching binding found for " + key);
        }

        var resolver = this.bindings.get(key);
        return type.cast(resolver.get());
    }
}
