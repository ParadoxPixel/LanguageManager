package nl.iobyte.languagemanager.generic.database.objects.types;

import nl.iobyte.languagemanager.generic.database.objects.Database;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

/**
 * Empty database implementation
 */
public class NullDatabase extends Database {

    /**
     * Get Database Connection
     * @return Connection
     */
    public Connection getConnection() {
        return null;
    }

    /**
     * Close Connection
     */
    public void closeConnection() { }

    /**
     * Execute statement
     * @param query String
     * @param objects Map<Integer, Object>
     * @return Boolean
     */
    @Override
    public boolean execute(String query, Map<Integer, Object> objects) {
        return false;
    }

    /**
     * Execute update statement
     * @param query String
     * @param objects Map<Integer, Object>
     * @return Integer
     */
    @Override
    public int executeUpdate(String query, Map<Integer, Object> objects) {
        return 0;
    }

    /**
     * Execute query
     * @param query String
     * @param objects Map<Integer, Object>
     * @return ArrayList<Map<String, Object>>
     */
    @Override
    public ArrayList<Map<String, Object>> executeQuery(String query, Map<Integer, Object> objects) {
        return new ArrayList<>();
    }

}
