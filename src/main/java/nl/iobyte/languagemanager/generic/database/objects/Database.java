package nl.iobyte.languagemanager.generic.database.objects;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Database {

    public abstract Connection getConnection() throws SQLException;

    public abstract void closeConnection();

    /**
     * Execute statement
     * @param query String
     * @param objects Map<Integer, Object>
     * @return Boolean
     */
    public boolean execute(String query, Map<Integer, Object> objects) {
        if(query == null || query.isEmpty())
            return false;

        boolean b = false;
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.closeOnCompletion();
            if(objects != null)
                for(Map.Entry<Integer, Object> entry : objects.entrySet())
                    statement.setObject(entry.getKey(), entry.getValue());

            b = statement.execute();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return b;
    }

    /**
     * Execute update statement
     * @param query String
     * @param objects Map<Integer, Object>
     * @return Integer
     */
    public int executeUpdate(String query, Map<Integer, Object> objects) {
        if (query == null || query.isEmpty())
            return 0;

        int i = 0;
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.closeOnCompletion();
            for (Map.Entry<Integer, Object> entry : objects.entrySet())
                statement.setObject(entry.getKey(), entry.getValue());

            i = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    /**
     * Execute query
     * @param query String
     * @param objects Map<Integer, Object>
     * @return ArrayList<Map<String, Object>>
     */
    public ArrayList<Map<String, Object>> executeQuery(String query, Map<Integer, Object> objects) {
        if (query == null || query.isEmpty())
            return null;

        ArrayList<Map<String, Object>> set = null;
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.closeOnCompletion();
            if (objects != null)
                for (Map.Entry<Integer, Object> entry : objects.entrySet())
                    statement.setObject(entry.getKey(), entry.getValue());

            set = new ArrayList<>();

            ResultSet result = statement.executeQuery();
            ResultSetMetaData metadata = result.getMetaData();
            int columnCount = metadata.getColumnCount();
            while(result.next()) {
                Map<String, Object> map = new HashMap<>();
                for(int i = 1; i <= columnCount; i++)
                    map.put(metadata.getColumnName(i), result.getObject(i));

                set.add(map);
            }

            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return set;
    }

}

