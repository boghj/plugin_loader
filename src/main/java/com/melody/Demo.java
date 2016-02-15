package com.melody;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class Demo {

    static String DIR_PLUGINS = "plugins";

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {

        PluginLoader pluginLoader = new PluginLoader();
        Collection<Object> plugins = pluginLoader.load(DIR_PLUGINS);

        for (Object plugin : plugins) {
            new PluginRunner(plugin).execute();
        }
    }
}
