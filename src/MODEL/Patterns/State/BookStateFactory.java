/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.State;

/**
 *
 * @author hussien
 */
import java.util.HashMap;
import java.util.Map;

public class BookStateFactory {
    private static final Map<String, BookState> stateMap = new HashMap<>();

    static {
        stateMap.put("available", new AvailableState());
        stateMap.put("unavailable", new UnavailableState());
        stateMap.put("requested", new RequestedState());
        stateMap.put("reserved", new ReservedState());
        stateMap.put("checkedout", new CheckedOutState());
        stateMap.put("overdue", new OverdueState());
        stateMap.put("returned", new ReturnedState());
    }

    public static BookState getState(String status) {
        return stateMap.getOrDefault(status, new AvailableState());
    }
}
