package at.srsyntax.antivpn.database;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * CONFIDENTIAL
 * Unpublished Copyright (c) 16.11.2020 Marcel Haberl, All Rights Reserved.
 * -
 * NOTICE:  All information contained herein is, and remains the property of Marcel Haberl. The intellectual and technical concepts contained
 * herein are proprietary to Marcel Haberl and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained
 * from Marcel Haberl.  Access to the source code contained herein is hereby forbidden to anyone without written permission
 * Confidentiality and Non-disclosure agreements explicitly covering such access.
 * -
 * The copyright notice above does not evidence any actual or intended publication or disclosure  of  this source code, which includes
 * information that is confidential and/or proprietary, and is a trade secret, of Marcel Haberl.   ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC  PERFORMANCE,
 * OR PUBLIC DISPLAY OF OR THROUGH USE  OF THIS  SOURCE CODE  WITHOUT  THE EXPRESS WRITTEN CONSENT OF Marcel Haberl IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE
 * LAWS AND INTERNATIONAL TREATIES.  THE RECEIPT OR POSSESSION OF  THIS SOURCE CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS
 * TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR SELL ANYTHING THAT IT  MAY DESCRIBE, IN WHOLE OR IN PART.
 */
public class DatabaseConfig {

    private final String username, password, database, host;
    private final int port;

    public DatabaseConfig(String username, String password, String database, String host, int port) {
        this.username = username;
        this.password = password;
        this.database = database;
        this.host = host;
        this.port = port;
    }

    public DatabaseConfig() {
        this.username = null;
        this.password = null;
        this.database = null;
        this.host = null;
        this.port = 0;
    }

    public DatabaseConfig load(Plugin plugin) throws IOException, InterruptedException {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdirs();

        final File file = new File(plugin.getDataFolder(), "database.yml");
        final Configuration configuration = YamlConfiguration.getProvider(YamlConfiguration.class).load(file);

        if (file.exists()) {
            return new DatabaseConfig(
                    configuration.getString("username"),
                    configuration.getString("password"),
                    configuration.getString("database"),
                    configuration.getString("host"),
                    configuration.getInt("port")
            );
        } else {
            plugin.getLogger().severe("Database configuration does not exist.");
            plugin.getLogger().severe("Create configuration...");

            file.createNewFile();
            configuration.set("username", "Gamster");
            configuration.set("password", "password");
            configuration.set("database", "anitvpn");
            configuration.set("host", "localhost");
            configuration.set("port", 3306);

            plugin.getLogger().severe("Please change the configuration!");
            Thread.sleep(5000);
            plugin.getProxy().stop("Please change the database configuration");
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
