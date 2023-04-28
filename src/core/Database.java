package core;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    Connection connection = null;
    PreparedStatement statement = null;

    public Database(HashMap<String, String> config, String username, String password) throws SQLException {
        String currentUrlString = "jdbc:" + config.get("database") + "://" + config.get("host") + ":" + config.get("port")
                + "/" + config.get("dbname");
        connection = DriverManager.getConnection(currentUrlString, username, password);
    }

    public boolean isValid() throws SQLException {
        return connection.isValid(5);
    }

    public Database query(String query) throws SQLException {
        this.statement = this.connection.prepareStatement(query);
        this.statement.execute();
        return this;
    }

    public Database query(String query, ArrayList<String> param) throws SQLException {
        this.statement = this.connection.prepareStatement(query);
        int index = 1;
        for (var it = param.iterator(); it.hasNext(); index++) {
            this.statement.setString(index, it.next());
        }
        this.statement.execute();
        return this;
    }

    public ResultSet get() throws SQLException {
        return this.statement.getResultSet();
    }
}
