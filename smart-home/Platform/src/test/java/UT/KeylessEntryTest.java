package tartan.test.java.UT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tartan.smarthome.resources.iotcontroller.IoTValues;
import tartan.smarthome.resources.StaticTartanStateEvaluator;
import java.util.*;

class HomeState {
    private final Map<String, Object> state = new HashMap<>();

    HomeState() {
        initializeDefaults();
    }

    private void initializeDefaults() {
        state.put(IoTValues.LIGHT_STATE, false);
        state.put(IoTValues.PROXIMITY_STATE, false);
        state.put(IoTValues.PHONE_PROXIMITY_STATE, false);
        state.put(IoTValues.LOCK_STATE, true);
        state.put(IoTValues.INTRUDER_STATE, false);
        state.put(IoTValues.TARGET_TEMP, 59);
        state.put(IoTValues.TEMP_READING, 59);
        state.put(IoTValues.DOOR_STATE, false);
        state.put(IoTValues.AWAY_TIMER, false);
        state.put(IoTValues.ALARM_STATE, true);
        state.put(IoTValues.HUMIDIFIER_STATE, true);
        state.put(IoTValues.HEATER_STATE, true);
        state.put(IoTValues.CHILLER_STATE, false);
        state.put(IoTValues.ALARM_ACTIVE, false);
        state.put(IoTValues.HVAC_MODE, "Heater");
        state.put(IoTValues.ALARM_PASSCODE, "");
        state.put(IoTValues.GIVEN_PASSCODE, "");
    }

    void set(String key, Object value) {
        state.put(key, value);
    }

    Map<String, Object> getState() {
        return new HashMap<>(state);
    }
}

class StateUpdater {
    static Map<String, Object> evaluate(HomeState homeState, StringBuffer log) {
        return new StaticTartanStateEvaluator().evaluateState(homeState.getState(), log);
    }
}

class UT_KeylessEntry {
    private HomeState homeState;
    private StringBuffer log;

    @BeforeEach
    void setUp() {
        homeState = new HomeState();
        log = new StringBuffer();
    }

    @Test
    void testIfHouseUnlocked() {
        homeState.set(IoTValues.PHONE_PROXIMITY_STATE, true);
        homeState.set(IoTValues.LOCK_STATE, true);

        Boolean newLockState = (Boolean) StateUpdater.evaluate(homeState, log).get(IoTValues.LOCK_STATE);
        assertFalse(newLockState, "The door should be unlocked if the owner is nearby");
    }

    @Test
    void testIfHouseLocked() {
        homeState.set(IoTValues.PHONE_PROXIMITY_STATE, false);
        homeState.set(IoTValues.LOCK_STATE, true);

        Boolean newLockState = (Boolean) StateUpdater.evaluate(homeState, log).get(IoTValues.LOCK_STATE);
        assertTrue(newLockState, "The door should be locked if the owner is away");
    }

    @Test
    void testNotification() {
        homeState.set(IoTValues.PHONE_PROXIMITY_STATE, true);
        homeState.set(IoTValues.INTRUDER_STATE, true);
        homeState.set(IoTValues.LOCK_STATE, true);

        Map<String, Object> newState = StateUpdater.evaluate(homeState, log);
        Boolean newLockState = (Boolean) newState.get(IoTValues.LOCK_STATE);

        assertTrue(newLockState, "The door should be locked if there is an intruder");
        assertTrue(log.toString().contains("Intruder is near by. Cannot lock door"), "The system should notify the owner via logs");
    }
}
