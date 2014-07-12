package com.forgeessentials.api.permissions;

import com.forgeessentials.api.permissions.query.PermQuery;
import com.forgeessentials.api.permissions.query.PermQuery.PermResult;
import com.forgeessentials.api.permissions.query.PropQuery;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public interface IPermissionsHelper {
    // Javadocs please

    /**
     * Check if a permissions is allowed
     *
     * @param query A PermQuery object
     * @return true if allowed, false if not.
     */

    boolean checkPermAllowed(PermQuery query);

    /**
     * Check if a permissions is allowed. Use if you do not want to create PermQuery objects.
     *
     * @param player
     * @param node
     * @return true if allowed, false if not.
     */
    boolean checkPermAllowed(EntityPlayer player, String node);

    PermResult checkPermResult(PermQuery query);

    /**
     * populates the given PropQuery with a value.
     */
    void getPermissionProp(PropQuery query);

    /**
     * Create a group within a zone
     *
     * @param groupName Name of the group
     * @param zoneName  Name of the zone the group is under
     * @param prefix    Chat prefix
     * @param suffix    Chat suffix
     * @param parent    Parent group
     * @param priority  Priority that the group should be checked in
     * @return Group created
     */
    Group createGroupInZone(String groupName, String zoneName, String prefix, String suffix, String parent, int priority);

    /**
     * Set a permissions for a player
     *
     * @param username   The player's username
     * @param permission The permissions node name
     * @param allow      Is the permissions allowed or denied
     * @param zoneID     The zone in which the permissions takes effect
     * @return
     */
    String setPlayerPermission(String username, String permission, boolean allow, String zoneID);

    /**
     * Set a permissions for a group
     *
     * @param group   The group name
     * @param permission The permissions node name
     * @param allow      Is the permissions allowed or denied
     * @param zoneID     The zone in which the permissions takes effect
     * @return
     */
    String setGroupPermission(String group, String permission, boolean allow, String zoneID);

    /**
     * Set a permissions prop for a player
     *
     * @param username   The player's username
     * @param permission The permissions node name
     * @param value      Value of the permissions prop
     * @param zoneID     The zone in which the permissions takes effect
     * @return
     */
    String setPlayerPermissionProp(String username, String permission, String value, String zoneID);

    /**
     * Set a permissions for a player
     *
     * @param username   The player's username
     * @param permission The permissions node name
     * @param value      Value of the permissions prop
     * @param zoneID     The zone in which the permissions takes effect
     * @return
     */
    String setGroupPermissionProp(String group, String permission, String value, String zoneID);

    ArrayList<Group> getApplicableGroups(EntityPlayer player, boolean includeDefaults);

    ArrayList<Group> getApplicableGroups(String player, boolean includeDefaults, String zoneID);

    Group getGroupForName(String name);

    Group getHighestGroup(EntityPlayer player);

    ArrayList<String> getPlayersInGroup(String group, String zone);

    String setPlayerGroup(String group, String player, String zone);

    String addPlayerToGroup(String group, String player, String zone);

    String clearPlayerGroup(String group, String player, String zone);

    String clearPlayerPermission(String player, String node, String zone);

    String clearPlayerPermissionProp(String player, String node, String zone);

    void deleteGroupInZone(String group, String zone);

    boolean updateGroup(Group group);

    String clearGroupPermission(String name, String node, String zone);

    String clearGroupPermissionProp(String name, String node, String zone);

    ArrayList<Group> getGroupsInZone(String zoneName);

    String getPermissionForGroup(String target, String zone, String perm);

    String getPermissionPropForGroup(String target, String zone, String perm);

    String getPermissionForPlayer(String target, String zone, String perm);

    String getPermissionPropForPlayer(String target, String zone, String perm);

    ArrayList getPlayerPermissions(String target, String zone);

    ArrayList getPlayerPermissionProps(String target, String zone);

    ArrayList getGroupPermissions(String target, String zone);

    ArrayList getGroupPermissionProps(String target, String zone);

    String getEPPrefix();

    void setEPPrefix(String ePPrefix);

    String getEPSuffix();

    void setEPSuffix(String ePSuffix);

    Group getDEFAULT();

    String getEntryPlayer();
}
