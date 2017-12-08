/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlecruiser;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author phoenix
 */
public class DBHelper {

    private Connection connection = null;

    public DBHelper(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public int signIn(String username, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM admirals WHERE admiral_name = ? AND password = ?");
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        return (result.first()) ? (result.getInt("id")) : (-1);
    }

    public int signUp(String username, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM admirals WHERE admiral_name = ?");
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        if (result.first()) {
            return -1;
        } else {
            statement = connection.prepareStatement("INSERT INTO admirals (admiral_name, password) VALUES (?, ?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            return signIn(username, password);
        }
    }

    public List<Ship> downloadShips() throws SQLException {
        LinkedList<Ship> ans = new LinkedList<>();
        Statement statement = connection.createStatement();
        ResultSet r = statement.executeQuery("SELECT * FROM ships");
        while (r.next()) {
            //String shipName, String shipDescription, int cost, int signature, int l_damage, int k_damage, int m_damage, int hull, int armor, int shield, int MDS, int acc, int mobility
            ans.add(new Ship(r.getInt("id"),
                    r.getString("ship_name"),
                    r.getString("description"),
                    r.getInt("cost"),
                    r.getInt("signature"),
                    r.getInt("lazer_damage"),
                    r.getInt("kinetic_damage"),
                    r.getInt("missile_damage"),
                    r.getInt("hull"),
                    r.getInt("armor"),
                    r.getInt("shield"),
                    r.getInt("MDS"),
                    r.getInt("acc"),
                    r.getInt("mobility")));
        }
        return ans;
    }

    public int createFleet(String fleetName, List<Ship> ships, List<Integer> count, int admiral_id) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM fleets WHERE fleetName = ?");
        pStatement.setString(1, fleetName);
        ResultSet result = pStatement.executeQuery();
        if (result.first()) {
            return -1;
        } else {
            Statement statement = connection.createStatement();
            if (fleetName == null) System.err.println("1.5");
            pStatement = connection.prepareStatement("INSERT INTO fleets (fleetName, admiral_id) VALUES (?, ?)");
            pStatement.setString(1, fleetName);
            pStatement.setInt(2, admiral_id);
            pStatement.executeUpdate();
            pStatement = connection.prepareStatement("SELECT id FROM fleets WHERE fleetName = ?");
            pStatement.setString(1, fleetName);
            result = pStatement.executeQuery();
            result.first();
            int fleetID = result.getInt("id");

            for (Ship ship : ships) {
                pStatement = connection.prepareStatement("INSERT INTO fleet_formation (fleet_id, ship_id, count) VALUES (?, ?, ?)");
                pStatement.setInt(1, fleetID);
                pStatement.setInt(2, ship.id);
                pStatement.setInt(3, count.get(ships.indexOf(ship)));
                if (count.get(ships.indexOf(ship)) > 0) {
                    pStatement.executeUpdate();
                }
            }
            return fleetID;
        }
    }

    public int letsBattleBegin(int fleet_1st, int fleet_2nd, List<Action> log, boolean victory_1st) throws SQLException {
        PreparedStatement pStatement = connection.prepareStatement("INSERT INTO battles (1st_id, 2nd_id, victory) "
                + "VALUES (?, ?, ?)");
        pStatement.setInt(1, fleet_1st);
        pStatement.setInt(2, fleet_2nd);
        pStatement.setBoolean(3, victory_1st);
        pStatement.executeUpdate();
        pStatement = connection.prepareStatement("SELECT MAX(id) from battles WHERE 1st_id = ? AND 2nd_id = ?");
        pStatement.setInt(1, fleet_1st);
        pStatement.setInt(2, fleet_2nd);
        ResultSet result = pStatement.executeQuery();
        result.first();
        int battle_id = result.getInt(1);

        for (Action action : log) {
            pStatement = connection.prepareStatement("INSERT INTO battle_log (battle_id, step, target, source, rocket_atack, lazor_atack, keenetic_atack) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            pStatement.setInt(1, battle_id);
            pStatement.setInt(2, action.step);
            pStatement.setString(3, action.target);
            pStatement.setString(4, action.source);
            pStatement.setInt(5, action.m_attack);
            pStatement.setInt(6, action.l_attack);
            pStatement.setInt(7, action.k_attack);
            pStatement.executeUpdate();
        }
        return battle_id;
    }

    public int getFleetID(String fleetName) throws SQLException {
        PreparedStatement p = connection.prepareStatement("SELECT id FROM fleets WHERE fleetName = ?");
        p.setString(1, fleetName);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            return r.getInt("id");
        } else {
            return -1;
        }
    }
    
    public List<Ship> downloadFleet(int fleet_id, List<Integer> count) throws SQLException {
        LinkedList<Ship> ans = new LinkedList<>();
        PreparedStatement pStatement = connection.prepareStatement("SELECT ship_id, count FROM fleet_formation WHERE fleet_id = ?");
        pStatement.setInt(1, fleet_id);
        ResultSet result = pStatement.executeQuery();
        while (result.next()) {
            count.add(result.getInt("count"));
            pStatement = connection.prepareStatement("SELECT * FROM ships WHERE id = ?");
            pStatement.setInt(1, result.getInt("ship_id"));
            ResultSet r = pStatement.executeQuery();
            if (!r.first()) System.err.println("" + result.getInt("ship_id"));
            ans.add(new Ship(r.getInt("id"),
                    r.getString("ship_name"),
                    r.getString("description"),
                    r.getInt("cost"),
                    r.getInt("signature"),
                    r.getInt("lazer_damage"),
                    r.getInt("kinetic_damage"),
                    r.getInt("missile_damage"),
                    r.getInt("hull"),
                    r.getInt("armor"),
                    r.getInt("shield"),
                    r.getInt("MDS"),
                    r.getInt("acc"),
                    r.getInt("mobility")));
        }
        return ans;
    }
    
    public List<String> getFleets() throws SQLException {
        List<String> ans = new LinkedList<>();
        PreparedStatement pSt = connection.prepareStatement("SELECT fleetName FROM fleets");
        ResultSet r = pSt.executeQuery();
        while (r.next()) {
            ans.add(r.getString("fleetName"));
        }
        return ans;
    }
    
    public void close() throws SQLException {
        connection.close();
    }
}
