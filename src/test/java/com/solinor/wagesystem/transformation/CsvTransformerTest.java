package com.solinor.wagesystem.transformation;

import com.solinor.wagesystem.model.InputWrapper;
import com.solinor.wagesystem.model.WageEntry;
import com.solinor.wagesystem.transformation.CsvTransformer;
import com.solinor.wagesystem.validation.InputValidationException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yolan
 */

public class CsvTransformerTest {
    private CsvTransformer transformer;

    @Before
    public void init() {
        transformer = new CsvTransformer();
    }

    @Test
    public void testCsvTransformerSimple() throws InputValidationException {
        InputWrapper input = new InputWrapper("Person Name,Person ID,Date,Start,End\n" +
                "Scott Scala,2,2.3.2014,6:00,7:00\n");

        List<WageEntry> actual = transformer.transform(input);
        WageEntry entry = actual.get(0);

        assertEquals(entry.getName(),"Scott Scala");
        assertEquals(entry.getId(),2);
        assertEquals(entry.getStart(),LocalDateTime.of(2014,3,2,6,0));
        assertEquals(entry.getEnd(),LocalDateTime.of(2014,3,2,7,0));

    }
    @Test
    public void testCsvTransformerDouble() throws InputValidationException {
        InputWrapper input = new InputWrapper("Person Name,Person ID,Date,Start,End\n" +
                "Scott Scala,2,2.3.2014,6:00,7:00\n"+
                "Janet Java,1,12.3.2014,16:00,2:00\n");

        List<WageEntry> actual = transformer.transform(input);

        WageEntry entry = actual.get(0);
        assertEquals(entry.getName(),"Scott Scala");
        assertEquals(entry.getId(),2);
        assertEquals(entry.getStart(),LocalDateTime.of(2014,3,2,6,0));
        assertEquals(entry.getEnd(),LocalDateTime.of(2014,3,2,7,0));

        WageEntry entry2 = actual.get(1);
        assertEquals(entry2.getName(),"Janet Java");
        assertEquals(entry2.getId(),1);
        assertEquals(entry2.getStart(), LocalDateTime.of(2014,3,12,16,0));
        assertEquals(entry2.getEnd(),LocalDateTime.of(2014,3,12,2,0));

    }
}
