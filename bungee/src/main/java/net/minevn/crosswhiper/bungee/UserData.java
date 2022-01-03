package net.minevn.crosswhiper.bungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.util.*;

public class UserData {
    private static final File root = new File(CWBungee.getInstance().getDataFolder(), "userdata");

//    private ProxiedPlayer player;
    private boolean blocked = false;
    private List<String> blackList = new ArrayList<>();
    private String lastMessage = null;

    public UserData(ProxiedPlayer player) {
        Configuration data = Configs.loadFromFile(new File(root, player.getUniqueId().toString() + ".yml"));
        if (data != null) {
            blocked = data.get("blocked", false);
            blackList = data.getStringList("black-list");
        }
        getDataMap().put(player.getUniqueId(), this);
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isBlackList(ProxiedPlayer other) {
        return blackList != null && blackList.contains(other.getName());
    }

    public List<String> getBlackList() {
        return blackList;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    //region static
    private static Map<UUID, UserData> data;

    private static Map<UUID, UserData> getDataMap() {
        if (data == null) data = new HashMap<>();
        return data;
    }

    public static UserData getData(ProxiedPlayer player) {
        return  getDataMap().get(player.getUniqueId());
    }
    //endregion
}
