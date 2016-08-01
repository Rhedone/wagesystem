package com.solinor.wagesystem.entry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by yolan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class EntryPointIntegrationTest {

    @Inject
    private EntryPoint ep;
    private String txt;

    @Before
    public void init() throws IOException {
        txt = "Person Name,Person ID,Date,Start,End\n" +
                "Scott Scala,2,2.3.2014,6:00,14:00\n";
    }

    @Test
    public void testEntryPointHappy() {
        ep.introduceWages(txt);
    }
}
