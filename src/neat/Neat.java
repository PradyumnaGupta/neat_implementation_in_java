package neat;

import data_structures.RandomHashSet;
import genome.ConnectionGene;
import genome.Genome;
import genome.NodeGene;
import org.w3c.dom.Node;

import java.util.HashMap;

public class Neat {

    public final static int MAX_NODES = (int)Math.pow(2,20);
    public final double C1= 1,C2=1,C3=1;
    private final double CP = 4;

    private double WEIGHT_SHIFT_STRENGTH = 0.3;
    private double WEIGHT_RANDOM_STRENGTH = 1;

    private HashMap<ConnectionGene,ConnectionGene> all_connections = new HashMap<>();
    private RandomHashSet<NodeGene> all_nodes = new RandomHashSet<>();
    private int input_size;
    private int output_size;
    private int max_clients;

    public Neat(int max_clients,int input_size,int output_size){
        this.reset(max_clients,input_size,output_size);
    }

    public Genome empty_genome(){
        Genome g = new Genome(this);
        for(int i=0;i<input_size+output_size;i++){
            g.getNodes().add(getNode(i+1));
        }
        return g;
    }

    public void reset(int max_clients,int input_size,int output_size){
        this.max_clients = max_clients;
        this.input_size = input_size;
        this.output_size = output_size;

        all_connections.clear();
        all_nodes.clear();

        for(int i=0;i<input_size;i++){
            NodeGene n = getNode();
            n.setX(0.1);
            n.setY((i+1)/(double)(input_size+1));
        }

        for(int i=0;i<output_size;i++){
            NodeGene n = getNode();
            n.setX(0.9);
            n.setY((i+1)/(double)(output_size+1));
        }
    }

    public NodeGene getNode(){
        NodeGene n = new NodeGene(all_nodes.size()+1);
        all_nodes.add(n);
        return n;
    }

    public NodeGene getNode(int id){
        if(id<all_nodes.size()) return all_nodes.get(id-1);
        else return getNode();
    }

    public ConnectionGene getConnection(ConnectionGene con){
        ConnectionGene c = new ConnectionGene(con.getFrom(), con.getTo());
        c.setInnovation_number(con.getInnovation_number());
        c.setEnabled(con.isEnabled());
        c.setWeight(con.getWeight());
        return c;
    }

    public ConnectionGene getConnection(NodeGene node1,NodeGene node2){
        ConnectionGene connectionGene = new ConnectionGene(node1,node2);

        if(all_connections.containsKey(connectionGene)){
            connectionGene.setInnovation_number(all_connections.get(connectionGene).getInnovation_number());
        }
        else {
            connectionGene.setInnovation_number(all_connections.size()+1);
            all_connections.put(connectionGene,connectionGene);
        }

        return connectionGene;
    }

    public double getC1() {
        return C1;
    }

    public double getC2() {
        return C2;
    }

    public double getC3() {
        return C3;
    }

    public int getInput_size() {
        return input_size;
    }

    public int getOutput_size() {
        return output_size;
    }

    public double getWEIGHT_SHIFT_STRENGTH() {
        return WEIGHT_SHIFT_STRENGTH;
    }

    public double getWEIGHT_RANDOM_STRENGTH() {
        return WEIGHT_RANDOM_STRENGTH;
    }

    public double getCP() {
        return CP;
    }

    public static void main(String[] args){
        Neat neat = new Neat(3,3,3);
        Genome g = neat.empty_genome();
        System.out.println(g.getNodes().size());
    }
}
