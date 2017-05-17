
package cenidet.cc.publictransit.interfaces;

import cenidet.cc.publictransit.dto.Stop;
import java.sql.SQLException;
import java.util.ArrayList;


public interface StopInterface {
    public ArrayList<Stop> getStopsByJourneyId(int id) throws SQLException;
    public ArrayList<Stop> getAllStops() throws SQLException;
}
