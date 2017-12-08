/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nskgoserver;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author phoenix
 */

@XmlRootElement
public class Marker{
    private int id;
    private double latitude;
    private double longtitude;
    private String name;
    private String info;
    
    public Marker() {}
    
    public Marker(int id, double latitude, double longtitude, String name, String info) {
        this.id = id;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.name = name;
        this.info = info;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longtitude
     */
    public double getLongtitude() {
        return longtitude;
    }

    /**
     * @param longtitude the longtitude to set
     */
    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }
    
    @Override
    public String toString() {
        return String.format("id: %d\nlatitude: %f\nlongtitude: %f\nname: %s\ninfo: %s", id, latitude, longtitude, name, info);
    }
    
}
