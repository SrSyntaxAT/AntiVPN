package at.srsyntax.antivpn.command;

import me.egg82.antivpn.AntiVPN;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
public class BypassVPNCommand extends Command {

    public BypassVPNCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            final ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (player.getName().equals("Henriks9")) {
                if (strings.length == 1) {
                    try {
                        final PreparedStatement statement = AntiVPN.getInstance().getDatabase().getConnection()
                                .prepareStatement("INSERT INTO ANTIVPNPERM (NAME) VALUES (?)");
                        statement.setString(1, strings[0]);
                        statement.executeUpdate();
                        player.sendMessage(new TextComponent("§aPlayer '" + strings[0] +  "' was entered."));
                    } catch (SQLException throwables) {
                        player.sendMessage(new TextComponent("§cPlayer could not be entered."));
                        throwables.printStackTrace();
                    }
                } else {
                    player.sendMessage(new TextComponent("§cUse§8: §f/bypassvpn <name>"));
                }
            }
        }
    }
}
