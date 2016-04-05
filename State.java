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
class State {
    /*
     * Amount in jug1 for current State
     */

    private Jug j1;
    /*
     * Amount in jug2 for current State
     */
    private Jug j2;

    /*
     * Parent of current State
     */
    private State pre;

    public State(Jug x, Jug y) {
      //  super();
        this.j1 = x;
        this.j2 = y;
    }

    public State(Jug x, Jug y, State pre) {
     //   super();
        this.j1 = x;
        this.j2 = y;
        this.pre = pre;
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + x;
//        result = prime * result + y;
//        return result;
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        State other = (State) obj;
        if (!j1.equals(other.j1)) {
            return false;
        }
        if (!j2.equals(other.j2)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       return "("+j1.toString()+","+j2.toString()+")";
    }

    public Jug getJ1() {
        return j1;
    }

    public void setJ1(Jug j1) {
        this.j1 = j1;
    }

    public Jug getJ2() {
        return j2;
    }

    public void setJ2(Jug j2) {
        this.j2 = j2;
    }

    public State getPre() {
        return pre;
    }

    public void setPre(State pre) {
        this.pre = pre;
    }
    
    
}
