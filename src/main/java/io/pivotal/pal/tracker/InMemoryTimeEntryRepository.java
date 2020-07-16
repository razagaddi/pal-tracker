package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;


public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    HashMap<Long,TimeEntry> storage = new HashMap<Long,TimeEntry>();
    Long counter =0l;

    public TimeEntry create(TimeEntry any) {
        counter = counter +1;
        Long id = Long.valueOf(counter);
        any.setId(id);
        storage.put(id,any);
        return any;
    }

    public TimeEntry find(long timeEntryId) {
        return storage.get(timeEntryId);
    }

    public TimeEntry update(long eq, TimeEntry any) {
        TimeEntry timeEntry = null;
        if(find(eq) != null){
            any.setId(eq);
            storage.remove(eq);
           storage.put(eq,any);
           timeEntry = storage.get(eq);
        }

        return timeEntry;

    }

    public void delete(long timeEntryId) {
        storage.remove(timeEntryId);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(storage.values());
    }

}
