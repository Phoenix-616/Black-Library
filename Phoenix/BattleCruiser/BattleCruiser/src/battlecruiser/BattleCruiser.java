/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlecruiser;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phoenix
 */
public class BattleCruiser {

    private static void signIn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Login:");
        String login = sc.nextLine();
        System.out.println("Password:");
        String passwd = sc.nextLine();
        int t = -1;
        try {
            t = db.signIn(login, passwd);
        } catch (SQLException ex) {
            System.err.println("Error oqured while signing IN");
        }
        if (t < 0) {
            System.err.println("Incorrect pair login-password");
        } else {
            user_id = t;
            System.err.println("Your id: " + t);
        }
    }

    private static void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Login:");
        String login = sc.nextLine();
        System.out.println("Password:");
        String passwd = sc.nextLine();
        int t = -1;
        try {
            t = db.signUp(login, passwd);
        } catch (SQLException ex) {
            System.err.println("Error oqured while signing UP");
        }
        if (t < 0) {
            System.err.println("Login is non-free");
        } else {
            user_id = t;
            System.err.println("Your id: " + t);
        }
    }

    private static void newFleet() {
        List<Ship> availableShips = null;
        String fleetName;
        List<Ship> fleetFormation = new LinkedList<>();
        List<Integer> count = new LinkedList<>();
        try {
            availableShips = db.downloadShips();
        } catch (SQLException ex) {
            System.err.println("Failure on connection...");
            return;
        }
        int idx = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter fleet name");
        fleetName = sc.nextLine();
        while (true) {
            String str = sc.nextLine();
            switch (str) {
                case "ships":
                    idx = 0;
                    for (Ship ship : availableShips) {
                        System.out.println(String.format("%d. %s", idx, ship.shipName));
                        idx++;
                    }
                    break;
                case "info":
                    idx = Integer.parseInt(sc.nextLine());
                    System.out.println(availableShips.get(idx).toString());
                    break;
                case "add":
                    System.out.println("Ship number");
                    int num = Integer.parseInt(sc.nextLine());
                    System.out.println("Ships' count");
                    int c = Integer.parseInt(sc.nextLine());
                    idx = fleetFormation.indexOf(availableShips.get(num));
                    if (idx < 0) {
                        System.err.println("hm " + num);
                        fleetFormation.add(availableShips.get(num));
                        count.add(c);
                    } else {
                        c += count.get(idx);
                        count.remove(idx);
                        count.add(idx, c);
                    }
                    System.out.println(c + " " + availableShips.get(num).shipName + " added");
                    break;
                case "OK":
                    if (!fleetFormation.isEmpty()) {
                        try {
                            db.createFleet(fleetName, fleetFormation, count, user_id);
                        } catch (SQLException ex) {
                            Logger.getLogger(BattleCruiser.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        idx = 0;
                        for (Ship ship : fleetFormation) {
                            System.out.println(ship.shipName + " " + count.get(idx));
                            idx++;
                        }
                    }
                    return;
                case "cancel":
                    return;
            }
        }
    }

    private static void newBattle() {
        List<String> fleets = null;
        try {
            fleets = db.getFleets();
        } catch (SQLException ex) {
            Logger.getLogger(BattleCruiser.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        Scanner sc = new Scanner(System.in);
        String str, f1 = null, f2 = null;
        while (true) {
            try {
                str = sc.nextLine();
                switch (str) {
                    case "fleets":
                        for (String ship : fleets) {
                            System.out.println(fleets.indexOf(ship) + " " + ship);
                        }
                        break;
                    case "fleet info":
                        System.out.println("Enter fleet's name");
                        str = sc.nextLine();
                        List<Integer> count = new LinkedList<>();
                        List<Ship> fleet = db.downloadFleet(db.getFleetID(str), count);
                        for (int i = 0; i < fleet.size(); i++) {
                            System.out.println(i + " " + fleet.get(i).shipName + " " + count.get(i));
                        }
                        break;
                    case "1st":
                        System.out.println("Enter fleet's name");
                        f1 = sc.nextLine();
                        break;
                    case "2nd":
                        System.out.println("Enter fleet's name");
                        f2 = sc.nextLine();
                        break;
                    case "OK":
                        if ((f1 != null) && (f2 != null)) {
                            List<Ship> f_1, f_2;
                            List<Integer> c_1, c_2;
                            c_1 = new LinkedList<>();
                            c_2 = new LinkedList<>();
                            f_1 = db.downloadFleet(db.getFleetID(f1), c_1);
                            f_2 = db.downloadFleet(db.getFleetID(f2), c_2);
                            Battle battle = new Battle(f_1, f_2, c_1, c_2);
                            List<Action> b_l = new LinkedList<>();
                            boolean f = battle.firstWin(b_l);
                            db.letsBattleBegin(db.getFleetID(f1), db.getFleetID(f2), b_l, f);
                        }
                        return;
                    case "cancel":
                        return;
                }
            } catch (Exception ex) {
                Logger.getLogger(BattleCruiser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static DBHelper db;
    private static int user_id = -1;

    public static void main(String[] args) {
        try {
            // TODO code application logic here
            db = new DBHelper("jdbc:mysql://127.0.0.1:3306/battlecruisers", "admin", "admin");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter command");
            while (true) {
                String str = sc.nextLine();
                if (str == null) {
                    break;
                }
                switch (str) {
                    case "quit":
                        db.close();
                        return;
                    case "sign in":
                        signIn();
                        break;
                    case "sign up":
                        signUp();
                        break;
                    default:
                        if (user_id < 0) {
                            System.err.println("Need user id");
                        } else {
                            switch (str) {
                                case "new fleet":
                                    newFleet();
                                    break;
                                case "new battle":
                                    newBattle();
                                    break;
                            }
                        }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BattleCruiser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
