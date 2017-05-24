
package org.cenidet.cc.publictransit.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import org.cenidet.cc.publictransit.dto.Stop;


public interface StopInterface {
    public ArrayList<Stop> getStopsByJourneyId(int id) throws SQLException;
    public ArrayList<Stop> getAllStops() throws SQLException;
}
