package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;
import java.util.Objects;

import static java.sql.ResultSet.*;

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
        this.statement.executeQuery(query);
        return this;
    }

    public Database query(String query, String param) throws SQLException {
        this.statement = this.connection.prepareStatement(query, TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
        this.statement.setString(1, param);
        this.statement.execute();
        return this;
    }

    public Database query(String query, ArrayList<?> param) throws SQLException {
        this.statement = this.connection.prepareStatement(query, TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
        int index = 1;
        for (var it = param.listIterator(); it.hasNext(); index++) {
            this.statement.setString(index, it.next().toString());
        }
        this.statement.execute();
        return this;
    }

    public ResultSet get() throws SQLException {
        return this.statement.getResultSet();
    }

}
