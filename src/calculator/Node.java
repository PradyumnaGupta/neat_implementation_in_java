package calculator;

import java.util.ArrayList;

public class Node implements Comparable<Node>{
    private double x;
    private double output;
    private ArrayList<Connection> connections = new ArrayList<>();

    public Node(double x){
        this.x = x;
    }

    public void calculate(){
        double s = 0;
        for(Connection c:connections){
            s+=c.getWeight() * c.getFrom().getOutput();
        }
        this.output = activation_function(s);
    }

    public double activation_function(double s){
        return 1d / (1+Math.exp(-s));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Connection> connections) {
        this.connections = connections;
    }


    @Override
    public int compareTo(Node o) {
        if(this.getX()>o.getX()) return -1;
        else if(this.getX()<o.getX()) return 1;
        else return 0;
    }
}
