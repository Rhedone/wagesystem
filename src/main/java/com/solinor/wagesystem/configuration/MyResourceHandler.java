package com.solinor.wagesystem.configuration;

import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;

import java.net.MalformedURLException;

/**
 * Created by yolan
 */
public class MyResourceHandler extends ResourceHandler {
    @Override
    public Resource getResource(String path) {
        Resource resource = Resource.newClassPathResource(path);
        if (resource == null || !resource.exists()) {
            resource = Resource.newClassPathResource("META-INF/resources" + path);
        }
        return resource;
    }
}
