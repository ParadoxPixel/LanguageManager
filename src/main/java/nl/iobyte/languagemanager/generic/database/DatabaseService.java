package nl.iobyte.languagemanager.generic.database;

import nl.iobyte.languagemanager.generic.database.objects.Database;
import nl.iobyte.languagemanager.generic.database.objects.types.NullDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseService {

    private final Map<String, Database> databases = new HashMap<>();

    /**
     * Add Database to DatabaseService
     * @param id String
     * @param database Database
     */
    public void addDatabase(String id, Database database) {
        if(id == null || id.isEmpty() || database == null)
            return;

        if(databases.containsKey(id))
            return;

        databases.put(id, database);
    }

    /**
     * Get Database
     * @param id String
     * @return Database
     */
    public Database getDatabase(String id) {
        if(id == null || id.isEmpty())
            return new NullDatabase();

        return databases.getOrDefault(id, new NullDatabase());
    }

    /**
     * Execute statement
     * @param id String
     * @param query String
     * @param objects Map<Integer, Object>
     * @return Boolean
     */
    public boolean execute(String id, String query, Map<Integer, Object> objects) {
        return getDatabase(id).execute(query, objects);
    }

    /**
     * Execute update statement
     * @param id String
     * @param query String
     * @param objects Map<Integer, Object>
     * @return Integer
     */
    public int executeUpdate(String id, String query, Map<Integer, Object> objects) {
        return getDatabase(id).executeUpdate(query, objects);
    }

    /**
     * Execute query
     * @param id String
     * @param query String
     * @param objects Map<Integer, Object>
     * @return ArrayList<Map<String, Object>>
     */
    public ArrayList<Map<String, Object>> executeQuery(String id, String query, Map<Integer, Object> objects) {
        return getDatabase(id).executeQuery(query, objects);
    }

    /**
     * Check if Database exists
     * @param id String
     * @return Boolean
     */
    public boolean hasDatabase(String id) {
        if(id == null || id.isEmpty())
            return false;

        return databases.containsKey(id);
    }

    /**
     * Remove Database
     * @param id String
     */
    public void removeDatabase(String id) {
        Database db = databases.remove(id);
        if(db == null)
            return;

        db.closeConnection();
    }

    /**
     * Close all databases
     */
    public void stop() {
        for(Database database : databases.values())
            database.closeConnection();
    }

}
