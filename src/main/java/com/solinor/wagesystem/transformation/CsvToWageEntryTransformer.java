package com.solinor.wagesystem.transformation;

import com.solinor.wagesystem.model.InputWrapper;
import com.solinor.wagesystem.model.WageEntry;
import com.solinor.wagesystem.validation.InputValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yolan
 */
@Component
public class CsvToWageEntryTransformer implements Transformer {


    public List<WageEntry> transform(InputWrapper input) throws InputValidationException {
        String[] splitInput = input.splitInput();
        List<WageEntry> wageEntries = new ArrayList<WageEntry>();

        for (int i = 1; i < splitInput.length; i++) {
            String[] pieces = splitInput[i].split(",");
            String name = pieces[0];
            int id = Integer.parseInt(pieces[1]);
            String date = pieces[2];
            String start = pieces[3];
            String end = pieces[4];

            LocalDateTime startDateTime = makeDateTime(date, start);
            LocalDateTime endDateTime = makeDateTime(date, end);

            WageEntry entry = new WageEntry(name, id, startDateTime, endDateTime);
            wageEntries.add(entry);
        }
        return wageEntries;
    }

    private LocalDateTime makeDateTime(String date, String time) {
        String[] splitDate = date.split("\\.");
        String[] splitTime = time.split(":");

        int dayOfMonth = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);

        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }
}
