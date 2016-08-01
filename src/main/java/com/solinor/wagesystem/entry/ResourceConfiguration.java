package com.solinor.wagesystem.entry;

/**
 * Created by yolan
 */

import com.solinor.wagesystem.handler.Handler;
import org.glassfish.jersey.server.ResourceConfig;

public class ResourceConfiguration extends ResourceConfig {
    public ResourceConfiguration() {
        packages("com.solinor.wagesystem.entry");
        register(EntryPoint.class);
        register(new MyApplicationBinder());
    }

}