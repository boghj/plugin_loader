package com.melody;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public interface IPlatformPlugin {
    String getContent() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;
    Date getDate() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;
}
