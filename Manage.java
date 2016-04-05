/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterjug;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author OsvaldoMaria
 */
public class Manage {

    private Node<State> root;
    private Queue<Node<State>> nodes = new LinkedList<>();
    private List<State> visited= new LinkedList<>();
    private List<Node<State>> goals = new LinkedList<>();

    public Manage(Jug j1, Jug j2) {
        State first=new State(j1, j2);
        root = new Node<>(first);
        visited.add(first);
        buildTree(root);
        
    }
    
            
    //pour water form one jug into another
    public Jug[] pour(Jug j1,Jug j2){
        System.out.println("temp jug 1 " +j1.toString()+"temp jug 2 " +j2.toString());
        if(!j1.isEmpty() && !j2.isFull()){
            Jug[] jugs = new Jug[2];
            Jug tempDar = new Jug(j1.getMaxCap(), j1.getMinCap(), j1.getCurrent());
            Jug tempRec = new Jug(j2.getMaxCap(), j2.getMinCap(), j2.getCurrent());
            while(!tempDar.isEmpty()&& !tempRec.isFull()){
                //take 1L from jug 1
                tempDar.setCurrent(tempDar.getCurrent()-1);
                //pour into jug 2
                tempRec.setCurrent(tempRec.getCurrent()+1);   
            }
            jugs[0]=tempDar;
            jugs[1] = tempRec;
                        System.out.println("temp 1 "+Arrays.toString(jugs));
            return jugs;
        }
        System.out.println("returning null pour");
        return null;
    }
    
    public Jug fill(Jug j){
        if(!j.isFull()){
            Jug temp= new Jug(j.getMaxCap(), j.getMinCap(), j.getCurrent());
         //   System.out.println("filling from "+j.toString());
            temp.setCurrent(temp.getMaxCap()); //System.out.println(" filling root "+root.getData().toString());
            return temp;
        }
        return null;
    }
    
    public Jug dump(Jug j){
        if(!j.isEmpty()){
            Jug temp= new Jug(j.getMaxCap(), j.getMinCap(), j.getCurrent());
            temp.setCurrent(0);
            return temp;
        }
        return null;
    }
    
    public boolean isGoal(Jug j){
     return j.getCurrent()==2;
    }
    
    public void callBuilder(){
        Node<State> n= nodes.poll();
        System.out.println("got here");
        if(n!=null){
            if(isGoal(n.getData().getJ1())){
            goals.add(n);
        }
         System.out.println("working on "+n.getData().toString());
        buildTree(n);
        } else{
            System.out.println("visited "+visited.size()+" nodes");
            for (State arg : visited) {
                System.out.println("" +arg.toString());
            }
         System.out.println("testing"+root.getChildren().toString());
         System.out.println("goals"+goals.toString());
        }
    }
    
    public List<Node<State>> getGoals(){
        return goals;
    }
    
    private void buildTree( Node<State> current){      
       State s = current.getData();
        //System.out.println("current node data "+s.toString());
       //boolean keepGoing=true;
       //while(keepGoing ){
           if(dump(s.getJ1())!=null){
               System.out.println("current node data right before dump 1 "+s.toString());
               //create new node with new state
                State st = new State(dump(s.getJ1()), s.getJ2());
                Node<State> n = new Node<>(st);
                //add as child to current node
                if(!visited.contains(st)){
                current.addChild(n);
                nodes.add(n);
                visited.add(st);
                }
           }
           if (dump(s.getJ2())!=null) {
                System.out.println("current node data after dump 2 "+s.toString());
               //create new node with new state
               State st= new State(s.getJ1(), dump(s.getJ2()));
               Node<State> n = new Node<>(st);
               //add as child to current node
                if(!visited.contains(st)){
                   current.addChild(n);
                   nodes.add(n);
                   visited.add(st);
               }
           }
           if (fill(s.getJ1())!=null) {
               //create new node with new state
                System.out.println("current node right before fill 1 "+s.toString());
                State st= new State(fill(s.getJ1()), s.getJ2());
                System.out.println("current node data after fill 1 "+st.toString());
               Node<State> n = new Node<>(st);
               //add as child to current node
                if(!visited.contains(st)){
                   current.addChild(n);
                   nodes.add(n);
                   visited.add(st);
               }
                //s=current.getData(); System.out.println("changed fffd "+current.getData().toString());
           }
        if (fill(s.getJ2()) != null) {
            //create new node with new state
            System.out.println("current node data right before fill 2 " + s.toString());
            State st = new State(s.getJ1(),fill(s.getJ2()) );
            System.out.println("current node data after fill 2 " + st.toString());
            Node<State> n = new Node<>(st);
            //add as child to current node
            if (!visited.contains(st)) {
                current.addChild(n);
                nodes.add(n);
                visited.add(st);
            }
            /*s=current.getData(); */ System.out.println("changed " + current.getData().toString());
        }
           if (fill(s.getJ1())!=null && fill(s.getJ2())!=null) {
            System.out.println("current node data right before fill both " + s.toString());
               State st = new State(fill(s.getJ1()), fill(s.getJ2()));
               System.out.println("current node data after fill 3 " + st.toString());
               Node<State> n = new Node<>(st);
               //add as child to current node
               if (!visited.contains(st)) {
                   current.addChild(n);
                   nodes.add(n);
                   visited.add(st);
               }
        }
           
           if (pour(s.getJ1(),s.getJ2())!=null) {
               
               //create new node with new state
               System.out.println("pouring from " + s.getJ1().toString() + " to " + s.getJ2().toString());
                Jug[] jugs= pour(s.getJ1(),s.getJ2());
                State st = new State(jugs[0],jugs[1]);
               Node<State> n = new Node<>(st);
               //add as child to current node
                if(!visited.contains(st)){
               current.addChild(n);
                   nodes.add(n);
                   visited.add(st);
               }
           }
           if (pour(s.getJ2(), s.getJ1())!=null) {
               //create new node with new state
               //create new node with new state
               System.out.println("pouring from "+s.getJ2().toString()+" to "+s.getJ1().toString() );
               Jug[] jugs = pour(s.getJ2(), s.getJ1());
               //jugs[0].setMaxCap(s.getJ1().getMaxCap()); System.out.println("max cap"+ s.getJ1().getMaxCap());
               //jugs[1].setMaxCap(s.getJ2().getMaxCap());System.out.println(" max cap"+ s.getJ2().getMaxCap());
               System.out.println("jugged "+Arrays.toString(jugs) );
               State st = new State(jugs[1], jugs[0]);
               System.out.println("after pouring "+st.toString());
               Node<State> n = new Node<>(st);
               //add as child to current node
               if (!visited.contains(st)) {
                   current.addChild(n);
                   nodes.add(n);
                   visited.add(st);
                   System.out.println("after saving pour "+n.getData().toString());
               }
           }
         //  System.out.println("node content "+nodes.toString());
           for (Node<State> arg : nodes) {
            System.out.print("nodes content " + arg.getData().toString());
            }
           callBuilder();
       
    }
    
    public Node<State> getRoot(){
        return root;
    }
    
   
}
