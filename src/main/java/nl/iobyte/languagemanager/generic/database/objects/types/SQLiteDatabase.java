package nl.iobyte.languagemanager.generic.database.objects.types;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import nl.iobyte.languagemanager.generic.database.objects.Database;
import nl.iobyte.languagemanager.generic.logger.LanguageLogger;
import org.bukkit.plugin.Plugin;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLiteDatabase extends Database {

    private final String dbName;
    private HikariDataSource source;

    public SQLiteDatabase(Plugin plugin, String dbName) {
        if(!dbName.endsWith(".db"))
            dbName += ".db";

        this.dbName = dbName;
        File dbFile = new File(plugin.getDataFolder(), dbName);
        if (!dbFile.exists()) {
            if(!dbFile.getParentFile().exists()) {
                if (!dbFile.getParentFile().mkdirs()) {
                    LanguageLogger.toConsole("Couldn't generate the parent directory of " + dbName + "!");
                    return;
                }
            }
            try {
                LanguageLogger.toConsole("Generating the " + dbName + "!");
                if (!dbFile.createNewFile()) {
                    LanguageLogger.toConsole("Couldn't generate the " + dbName + "!");
                    return;
                }
            } catch (IOException e) {
                LanguageLogger.toConsole("Couldn't generate the " + dbName + "!");
                return;
            }
        }

        HikariConfig config = new HikariConfig();
        config.setPoolName("LanguageManager");
        config.setUsername(null);
        config.setPassword(null);
        config.setDriverClassName("org.sqlite.JDBC");
        config.setConnectionTestQuery("SELECT 1");
        config.setMaximumPoolSize(10);

        config.setJdbcUrl("jdbc:sqlite:" + dbFile.getAbsolutePath());
        source = new HikariDataSource(config);
    }

    /**
     * Get Database Connection
     * @return Connection
     */
    public Connection getConnection() throws SQLException {
        return source.getConnection();
    }

    /**
     * Close Connection
     */
    public void closeConnection() {
        LanguageLogger.toConsole("Closing the database connection for " + dbName + "!");
        source.close();
    }

}