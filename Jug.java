/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterjug;


/**
 *
 * @author OsvaldoMaria
 */
public class Jug {

   private int maxCap;
   private int minCap;
   private int current;   

    public Jug(int maxCap, int minCap, int current) {
        this.maxCap = maxCap;
        this.minCap = minCap;
        this.current = current;
    }
    
    public int getMaxCap() {
        return maxCap;
    }
    public boolean isFull(){
        return maxCap==current;
    }
    public  boolean isEmpty(){
        return current==0;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }

    public int getMinCap() {
        return minCap;
    }

    public void setMinCap(int minCap) {
        this.minCap = minCap;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return current+"";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jug other = (Jug) obj;
        if (this.maxCap != other.maxCap) {
            return false;
        }
        if (this.minCap != other.minCap) {
            return false;
        }
        if (this.current != other.current) {
            return false;
        }
        return true;
    }
   

   
}
