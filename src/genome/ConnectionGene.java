package genome;

import neat.Neat;

public class ConnectionGene extends Gene{

    private NodeGene from;
    private NodeGene to;

    private int replaceIndex;

    private double weight;
    private boolean enabled;

    public ConnectionGene(NodeGene from,NodeGene to){
        this.from = from;
        this.to = to;
    }

    public NodeGene getFrom() {
        return from;
    }

    public void setFrom(NodeGene from) {
        this.from = from;
    }

    public NodeGene getTo() {
        return to;
    }

    public void setTo(NodeGene to) {
        this.to = to;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean equals(Object o){
        if(!(o instanceof ConnectionGene)) return false;
        else {
            ConnectionGene c = (ConnectionGene) o;
            return from.equals(c.getFrom()) && to.equals(c.getTo());
        }
    }

    public int hashCode(){
        return from.getInnovation_number() * Neat.MAX_NODES + to.getInnovation_number();
    }

    public int getReplaceIndex() {
        return replaceIndex;
    }

    public void setReplaceIndex(int replaceIndex) {
        this.replaceIndex = replaceIndex;
    }
}
