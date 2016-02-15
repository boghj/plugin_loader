package com.melody;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * load plugin jars from file system
 */

public class PluginLoader {


    // TODO file separator

    Collection<Object> load(final String dirPluginS) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Object> plugins = new ArrayList();

        JarClassLoader jcl = new JarClassLoader();
        /*DefaultContextLoader context = new DefaultContextLoader(jcl);
        context.loadContext();*/

        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString() + "/" + dirPluginS;
        System.out.println("Plugins root path: " + path);

        File[] pluginFolders = new File(path).listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
        for (File pFolder : pluginFolders) {
            String pFolderName = pFolder.getName();
            System.out.println("Loading plugin from path: " + pFolderName);
            File manifestFile = new File(path + "/" + pFolderName + "/MANIFEST");
            String manifest = FileUtils.readFileToString(manifestFile);
            String[] lines = manifest.split("\n");
            System.out.println("Plugin info for " + pFolderName + ":");
            String pluginClass = null;
            for (String line : lines) {
                System.out.println(line);
                String[] pair = line.split(":");
                if (pair[0].equalsIgnoreCase("Plugin-Class")) {
                    pluginClass = pair[1].trim();
                }
            }

            Collection<File> pluginFiles = FileUtils.listFiles(new File(path + "/" + pFolderName), new String[]{"jar"}, false);
            for (File f : pluginFiles) {
                System.out.println(f.getName());
                jcl.add(new FileInputStream(f.getAbsolutePath()));
                JclObjectFactory factory = JclObjectFactory.getInstance();
                Object plugin = factory.create(jcl, pluginClass);
                // TODO verify plugin has the desired methods
                plugins.add(plugin);
            }
        }


        return plugins;
    }

}
