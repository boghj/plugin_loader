package com.melody;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class PluginRunner implements IPlatformPlugin {

    private Object plugin;

    public PluginRunner(Object plugin) {
        this.plugin = plugin;
    }

    public void execute() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println(this.getContent() + " || " + this.getDate());
    }

    public String getContent() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class noParams[] = {};
        Method method = plugin.getClass().getDeclaredMethod("getContent", noParams);
        return (String) method.invoke(plugin, null);
    }

    public Date getDate() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class noParams[] = {};
        Method method = plugin.getClass().getDeclaredMethod("getDate", noParams);
        return (Date) method.invoke(plugin, null);
    }
}
