package tartan.smarthome.resources.iotcontroller;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages connection to the IoT house
 *
 * Project: LG Exec Ed Program
 * Copyright: Copyright (c) 2015 Jeffrey S. Gennari
 * Versions:
 * 1.0 November 2015 - initial version
 */
public class IoTConnectManager {
    // Connection to the house
    protected IoTConnection connection;

    /**
     * Set up the connection manager with a connection
     * @param conn the (established) connection
     */

    public void setIoTconnection(IoTConnection conn) {
        this.connection = conn;
    }

    /**
     * Disconnect from the house
     */
    public void disconnectFromHouse() {
        connection.disconnect();
    }

    /**
     * Get the state from the house
     * @return the new state of things
     */
    public synchronized Map<String,Object> getState() {

        System.out.println("Requesting state");

        synchronized (connection) {
            String update = connection.sendMessageToHouse(IoTValues.GET_STATE + IoTValues.MSG_END);
            LoggerFactory.getLogger(IoTConnectManager.class).info("TEST" + update);
            if (update == null) {
                return null;
            }

            return handleStateUpdate(update);
        }
    }



    /**
     * Send a state change request to the house
     * @param state the new state
     * @return true if the state was accepted; false otherwise
     */
    public synchronized Boolean setState(Map<String, Object> state) {

        StringBuffer newState = new StringBuffer();
        Set<String> keys = state.keySet();
        int count = 0;
        for (String key : keys) {

            if (key.equals(IoTValues.DOOR_STATE)) {

                Boolean newDoorState = (Boolean) state.get(key);
                newState.append(IoTValues.DOOR_STATE);
                newState.append(IoTValues.PARAM_EQ);
                if (newDoorState) {
                    newState.append(IoTValues.DOOR_OPEN);
                } else {
                    newState.append(IoTValues.DOOR_CLOSE);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            } else if (key.equals(IoTValues.LIGHT_STATE)) {
                Boolean newLightState = (Boolean) state.get(key);
                newState.append(IoTValues.LIGHT_STATE);
                newState.append(IoTValues.PARAM_EQ);
                if (newLightState) {
                    newState.append(IoTValues.LIGHT_ON);
                } else {
                    newState.append(IoTValues.LIGHT_OFF);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            } else if (key.equals(IoTValues.LIGHT_STATE)) {
                Boolean newLightState = (Boolean) state.get(key);
                newState.append(IoTValues.LIGHT_STATE);
                newState.append(IoTValues.PARAM_EQ);
                if (newLightState) {
                    newState.append(IoTValues.LIGHT_ON);
                } else {
                    newState.append(IoTValues.LIGHT_OFF);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            } else if (key.equals(IoTValues.ALARM_STATE)) {
                Boolean newAlarmState = (Boolean) state.get(key);
                newState.append(IoTValues.ALARM_STATE);
                newState.append(IoTValues.PARAM_EQ);
                if (newAlarmState) {
                    newState.append(IoTValues.ALARM_ENABLED);
                } else {
                    newState.append(IoTValues.ALARM_DISABLED);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            } else if (key.equals(IoTValues.ALARM_ACTIVE)) {
                Boolean newAlarmState = (Boolean) state.get(key);
                newState.append(IoTValues.ALARM_ACTIVE);
                newState.append(IoTValues.PARAM_EQ);
                if (newAlarmState) {
                    newState.append(IoTValues.ALARM_ON);
                } else {
                    newState.append(IoTValues.ALARM_OFF);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            }else if (key.equals(IoTValues.HUMIDIFIER_STATE)) {
                Boolean newHumidifierState = (Boolean) state.get(key);
                newState.append(IoTValues.HUMIDIFIER_STATE);
                newState.append(IoTValues.PARAM_EQ);
                if (newHumidifierState) {
                    newState.append(IoTValues.HUMIDIFIER_ON);
                } else {
                    newState.append(IoTValues.HUMIDIFIER_OFF);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            } else if (key.equals(IoTValues.CHILLER_STATE)) {
                Boolean newChillerState = (Boolean) state.get(key);
                newState.append(IoTValues.CHILLER_STATE);
                newState.append(IoTValues.PARAM_EQ);
                if (newChillerState) {
                    newState.append(IoTValues.CHILLER_ON);
                } else {
                    newState.append(IoTValues.CHILLER_OFF);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            } else if (key.equals(IoTValues.HEATER_STATE)) {
                Boolean newHeaterState = (Boolean) state.get(key);
                newState.append(IoTValues.HEATER_STATE);
                newState.append(IoTValues.PARAM_EQ);
                if (newHeaterState) {
                    newState.append(IoTValues.HEATER_ON);
                } else {
                    newState.append(IoTValues.HEATER_OFF);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            } else if (key.equals(IoTValues.LOCK_STATE)) {
                Boolean newLockState = (Boolean) state.get(key);
                newState.append(IoTValues.LOCK_STATE);
                newState.append(IoTValues.PARAM_EQ);
                if (newLockState) {
                    newState.append(IoTValues.LOCK_ON);
                } else {
                    newState.append(IoTValues.LOCK_OFF);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            } else if (key.equals(IoTValues.PHONE_PROXIMITY_STATE)) {
                Boolean newPhoneProximityState = (Boolean) state.get(key);
                newState.append(IoTValues.PHONE_PROXIMITY_STATE);
                newState.append(IoTValues.PARAM_EQ);
                if (newPhoneProximityState) {
                    newState.append(IoTValues.PHONE_HOME);
                } else {
                    newState.append(IoTValues.PHONE_AWAY);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            } else if (key.equals(IoTValues.LOCK_STATE_CHANGE_REQUESTED)) {
                Boolean newLockStateChangeRequseted = (Boolean) state.get(key);
                newState.append(IoTValues.LOCK_STATE_CHANGE_REQUESTED);
                newState.append(IoTValues.PARAM_EQ);
                if (newLockStateChangeRequseted) {
                    newState.append(IoTValues.CHANGE_REQUESTED);
                } else {
                    newState.append(IoTValues.CHANGE_UNREQUESTED);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            } else if (key.equals(IoTValues.INTRUDER_STATE)) {
                Boolean newIntruderState = (Boolean) state.get(key);
                newState.append(IoTValues.INTRUDER_STATE);
                newState.append(IoTValues.PARAM_EQ);
                if (newIntruderState) {
                    newState.append(IoTValues.INTRUDER_DETECTED);
                } else {
                    newState.append(IoTValues.INTRUDER_UNDETECTED);
                }
                count++;
                if (count<keys.size()) {
                    newState.append(IoTValues.PARAM_DELIM);
                }
            }
        }

        //newState.append(IoTValues.MSG_END); // append protocol request terminator
        StringBuffer msg
                = new StringBuffer(IoTValues.SET_STATE + IoTValues.MSG_DELIM + newState.toString() + IoTValues.MSG_END);
        System.out.println("New state for house: " + msg.toString());

        String response = null;
        synchronized (connection) {
            response = connection.sendMessageToHouse(msg.toString());
        }
        if (response == null) {
            System.out.println("No response");
            return false;
        }
        System.out.println("Response: " + response);

        return response.equals(IoTValues.OK);
    }

    /**
     * Process the new state reported by the house
     * @param stateUpdateMsg the new state message
     * @return the new state
     */
    private Map<String,Object> handleStateUpdate(String stateUpdateMsg) {

        if (stateUpdateMsg == null) {
            return null;
        }
        if (stateUpdateMsg.length() == 0) {
            return null;
        }

        System.out.println("State Update: " + stateUpdateMsg);
        Hashtable<String,Object> state = new Hashtable<String, Object>();

        String[] req = stateUpdateMsg.split(IoTValues.MSG_DELIM);

        // invalid state update
        if (req.length != 2) {
            return null;
        }
        // is this a state update
        String cmd = req[0];
        String body = req[1];

        if (!cmd.equals(IoTValues.STATE_UPDATE)) { // only message that comes from house
            return null;
        }

        if (String.valueOf(body.charAt(body.length()-1)).equals(IoTValues.MSG_END)) {
            body = body.substring(0, body.length() - 1);
        }
        if (body==null) {
            return null;
        }
        StringTokenizer pt = new StringTokenizer(body, IoTValues.PARAM_DELIM);

        // process the new state
        while (pt.hasMoreTokens()) {
            String param = pt.nextToken();
            String data[] = param.split(IoTValues.PARAM_EQ);

            if (data[0].equals(IoTValues.LIGHT_STATE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.LIGHT_STATE, true);
                } else {
                    state.put(IoTValues.LIGHT_STATE, false);
                }
            } else if (data[0].equals(IoTValues.ALARM_STATE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.ALARM_STATE, true);
                } else {
                state.put(IoTValues.ALARM_STATE, false);
                }
            } else if (data[0].equals(IoTValues.DOOR_STATE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.DOOR_STATE, true);
                } else {
                    state.put(IoTValues.DOOR_STATE, false);
                }
            }  else if (data[0].equals(IoTValues.HUMIDIFIER_STATE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.HUMIDIFIER_STATE, true);
                } else {
                    state.put(IoTValues.HUMIDIFIER_STATE, false);
                }
            } else if (data[0].equals(IoTValues.PROXIMITY_STATE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.PROXIMITY_STATE, true);
                } else {
                    state.put(IoTValues.PROXIMITY_STATE, false);
                }
            } else if (data[0].equals(IoTValues.ALARM_ACTIVE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.ALARM_ACTIVE, true);
                } else {
                    state.put(IoTValues.ALARM_ACTIVE, false);
                }
            } else if (data[0].equals(IoTValues.HEATER_STATE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.HEATER_STATE, true);
                } else {
                    state.put(IoTValues.HEATER_STATE, false);
                }
            } else if (data[0].equals(IoTValues.CHILLER_STATE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.CHILLER_STATE, true);
                } else {
                    state.put(IoTValues.CHILLER_STATE, false);
                }
            } else if (data[0].equals(IoTValues.TEMP_READING)) {
                Integer val = Integer.parseInt(data[1]);
                state.put(IoTValues.TEMP_READING, val);
            } else if (data[0].equals(IoTValues.HUMIDITY_READING)) {
                Integer val = Integer.parseInt(data[1]);
                state.put(IoTValues.HUMIDITY_READING, val);
            } else if (data[0].equals(IoTValues.HVAC_MODE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.HVAC_MODE, "Heater");
                } else {
                    state.put(IoTValues.HVAC_MODE, "Chiller");
                }
            } else if (data[0].equals(IoTValues.LOCK_STATE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.LOCK_STATE, true);
                } else {
                    state.put(IoTValues.LOCK_STATE, false);
                }
            } else if (data[0].equals(IoTValues.PHONE_PROXIMITY_STATE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.PHONE_PROXIMITY_STATE, true);
                } else {
                    state.put(IoTValues.PHONE_PROXIMITY_STATE, false);
                }
            } else if (data[0].equals(IoTValues.LOCK_STATE_CHANGE_REQUESTED)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.LOCK_STATE_CHANGE_REQUESTED, true);
                } else {
                    state.put(IoTValues.LOCK_STATE_CHANGE_REQUESTED, false);
                }
            } else if (data[0].equals(IoTValues.INTRUDER_STATE)) {
                Integer val = Integer.parseInt(data[1]);
                if (val == 1) {
                    state.put(IoTValues.INTRUDER_STATE, true);
                } else {
                    state.put(IoTValues.INTRUDER_STATE, false);
                }
            }
        }
        return state;
    }

    /**
     * Get the connected state
     * @return true if connected, false otherwise
     */
    public Boolean isConnected() {
        if (connection!=null) {
            return connection.isConnected();
        }
        return false;
    }
}
