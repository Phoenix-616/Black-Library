/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlecruiser;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author phoenix
 */
public class Battle {

    public List<Ship> fleet_1st, fleet_2nd;
    public List<Integer> count_1st, count_2nd;
    private Random rand = new Random(System.currentTimeMillis());

    public Battle(List<Ship> fleet_1st, List<Ship> fleet_2nd, List<Integer> count_1st, List<Integer> count_2nd) {
        this.fleet_1st = fleet_1st;
        this.fleet_2nd = fleet_2nd;
        this.count_1st = count_1st;
        this.count_2nd = count_2nd;
    }

    private List<Ship> getFullFleet(List<Ship> fleet, List<Integer> count) {
        List<Ship> ans = new LinkedList<>();
        int idx = 0;
        for (Ship ship : fleet) {
            for (int i = 1; i <= count.get(idx); i++) {
                Ship tmp = new Ship(ship);
                tmp.shipName = tmp.shipName + " " + i;
                ans.add(tmp);
            }
        }
        return ans;
    }

    private double getAbility(double acc, double mob, double sign) {
        double r = Math.tan(Math.PI * mob / 360);
        return (100 * 4 * Math.PI * Math.pow(sign, 3) / 3) / (Math.PI * r * r * acc / 3);
    }

    private List<Action> attack(List<Ship> a, List<Ship> d, int step) {
        List<Action> ans = new LinkedList<>();

        for (int i = 0; i < a.size();) {
            if (a.get(i).hull < 0) {
                a.remove(i);
            } else {
                i++;
            }
        }

        if (d.isEmpty()) return ans;
        for (Ship ship : a) {
            int targets = d.size();
            int tar = rand.nextInt(targets);
            Ship target = d.get(tar);
            if (rand.nextInt(101) <= getAbility(target.acc, target.mobility, target.signature)) {
                int l, k, m;
                k = ship.k_damage - target.armor;
                l = ship.l_damage - target.shield;
                m = ship.m_damage - target.MDS;
                if (k < 0) {
                    k = 0;
                }
                if (m < 0) {
                    m = 0;
                }
                if (l < 0) {
                    l = 0;
                }
                target.hull -= k + m + l;
                Action act;
                ans.add(act = new Action(ship.shipName, target.shipName, step, l, k, m));
                System.out.println(act);
            }
        }
        return ans;
    }

    public boolean firstWin(List<Action> battle_log) {
        List<Ship> f_1, f_2;
        f_1 = getFullFleet(fleet_1st, count_1st);
        f_2 = getFullFleet(fleet_2nd, count_2nd);
        int step = 0;
        while (!f_1.isEmpty() && !f_2.isEmpty()) {
            battle_log.addAll(attack(f_1, f_2, step));
            battle_log.addAll(attack(f_2, f_1, step));
            step++;
        }
        return f_2.isEmpty();
    }

}
