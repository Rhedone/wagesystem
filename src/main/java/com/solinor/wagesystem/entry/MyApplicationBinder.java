package com.solinor.wagesystem.entry;

import com.solinor.wagesystem.handler.Handler;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

    public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(EntryPoint.class).to(Handler.class);
    }
}