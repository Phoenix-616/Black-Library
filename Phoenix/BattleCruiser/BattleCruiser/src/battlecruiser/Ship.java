/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlecruiser;

/**
 *
 * @author phoenix
 */
public class Ship {

    public String shipName;
    public String shipDescription;
    public int cost;
    public int signature;
    public int l_damage, k_damage, m_damage;
    public int hull, armor, shield, MDS;
    public int acc, mobility;
    public int id;

    public Ship(int id, String shipName, String shipDescription, int cost, int signature, int l_damage, int k_damage, int m_damage, int hull, int armor, int shield, int MDS, int acc, int mobility) {
        this.id = id;
        this.shipName = shipName;
        this.shipDescription = shipDescription;
        this.cost = cost;
        this.signature = signature;
        this.l_damage = l_damage;
        this.k_damage = k_damage;
        this.m_damage = m_damage;
        this.hull = hull;
        this.armor = armor;
        this.shield = shield;
        this.MDS = MDS;
        this.acc = acc;
        this.mobility = mobility;
    }

    public Ship() {
    }

    public Ship(Ship o) {
        this.id = o.id;
        this.shipName = o.shipName;
        this.shipDescription = o.shipDescription;
        this.cost = o.cost;
        this.signature = o.signature;
        this.l_damage = o.l_damage;
        this.k_damage = o.k_damage;
        this.m_damage = o.m_damage;
        this.hull = o.hull;
        this.armor = o.armor;
        this.shield = o.shield;
        this.MDS = o.MDS;
        this.acc = o.acc;
        this.mobility = o.mobility;
    }
    
    @Override
    public String toString()
    {
        return String.format("Ship id: %d\n"
                + "Ship name: %s\n"
                + "Ship description: %s\n"
                + "Ship hull: %d\n"
                + "Ship armor: %d\n"
                + "Ship shield: %d\n"
                + "MDS: %d\n"
                + "Lazer damage: %d\n"
                + "Kinetic damage: %d\n"
                + "Missiles: %d\n"
                + "Ship acc: %d\n"
                + "Ship mobility: %d\n"
                + "Ship signature: %d\n"
                + "Ship cost: %d", 
                id,
                shipName, 
                shipDescription, 
                hull, 
                armor, 
                shield, 
                MDS, 
                l_damage, 
                k_damage, 
                m_damage, 
                acc, 
                mobility, 
                signature, 
                cost);
    }
}
