package com.solinor.wagesystem.entry;

/**
 * Created by yolan
 */

import org.glassfish.jersey.server.ResourceConfig;

public class ResourceConfiguration extends ResourceConfig {
    public ResourceConfiguration() {
        packages("com.solinor.wagesystem.entry");
        register(entrypoint.class);
    }

}