package cenidet.cc.publictransit.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PublicTransitDB {

    protected Connection connection;
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;
    protected String sqlStatement;

    
    protected void closeResources() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

}
