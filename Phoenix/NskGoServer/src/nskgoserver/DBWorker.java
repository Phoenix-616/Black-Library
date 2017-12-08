/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nskgoserver;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author phoenix
 */
public class DBWorker {

    private String url = null;
    private String user = null;
    private String password = null;
    private Connection connection = null;

    public DBWorker(String DBURL, String DBUser, String DBPassword) throws SQLException {
        url = DBURL;
        user = DBUser;
        password = DBPassword;

        connection = DriverManager.getConnection(url, user, password);
    }

    public boolean checkUser(String user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT user_id FROM users_table WHERE user_nickname = ?");
        statement.setString(1, user);
        ResultSet result = statement.executeQuery();
        return result.first();
    }

    public boolean checkUser(String user, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT user_id FROM users_table WHERE user_nickname = ? AND user_password = ?");
        statement.setString(1, user);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        return result.first();
    }
    
    public boolean checkRelation(int user_id, int marker_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT marker_state FROM user_marker_relation WHERE user_id = ? AND marker_id = ?");
        statement.setInt(1, user_id);
        statement.setInt(2, marker_id);
        ResultSet result = statement.executeQuery();
        return (result.first() && result.getBoolean("marker_state"));
    }

    public boolean createUser(String user, String password) throws SQLException {
        if (checkUser(user)) {
            return false;
        } else {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users_table (user_nickname, user_password) VALUES (?, ?)");
            statement.setString(1, user);
            statement.setString(2, password);
            statement.executeUpdate();
            return true;
        }
    }

    public int getUserId(String user) throws SQLException {
        int ans = -1;
        PreparedStatement statement = connection.prepareStatement(
                "SELECT user_id FROM users_table WHERE user_nickname = ?");
        statement.setString(1, user);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            ans = result.getInt("user_id");
        }
        return ans;
    }
    
    public void activateMarker(int user_id, int marker_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE user_marker_relation SET marker_state = 1 WHERE user_id = ? AND marker_id = ?");
        statement.setInt(1, user_id);
        statement.setInt(2, marker_id);
        statement.executeUpdate();
    }

    public List<Marker> getMarkersList() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM markers_table");
        List<Marker> markers = new LinkedList<>();
        Marker tmp;
        while (result.next()) {
            tmp = new Marker(
                    result.getInt("marker_id"),
                    result.getDouble("marker_latitude"),
                    result.getDouble("marker_longtitude"),
                    result.getString("marker_name"),
                    result.getString("marker_info"));
            markers.add(tmp);
        }
        return markers;
    }

    public void close() throws SQLException {
        connection.close();
    }

    @Override
    public void finalize() throws Throwable {
        close();
        super.finalize();
    }

}
