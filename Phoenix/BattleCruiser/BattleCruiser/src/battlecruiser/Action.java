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
public class Action {
    public String source;
    public String target;
    public int step;
    public int l_attack;
    public int k_attack;
    public int m_attack;
    
    public Action() {}

    public Action(String source, String target, int step, int l_attack, int k_attack, int m_attack) {
        this.source = source;
        this.target = target;
        this.step = step;
        this.l_attack = l_attack;
        this.k_attack = k_attack;
        this.m_attack = m_attack;
    }
    
    @Override
    public String toString() {
        return String.format("Source: %s\n"
                + "Target: %s\n"
                + "Step: %d\n"
                + "Lazer: %d\n"
                + "Keenetic: %d\n"
                + "Missiles: %d\n", source, target, step, l_attack, k_attack, m_attack);
    }
}
