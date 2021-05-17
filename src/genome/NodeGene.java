package genome;

public class NodeGene extends Gene{

    private double x,y;

    public NodeGene(int innovation_number){
        super(innovation_number);
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public boolean equals(Object o){
        if(!(o instanceof NodeGene)) return false;
        else return this.innovation_number == ((NodeGene) o).getInnovation_number();
    }

    public int hashcode(){
        return this.innovation_number;
    }
}
