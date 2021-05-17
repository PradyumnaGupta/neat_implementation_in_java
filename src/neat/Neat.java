package neat;

import data_structures.RandomHashSet;
import genome.ConnectionGene;
import genome.Genome;
import genome.NodeGene;
import org.w3c.dom.Node;

import java.util.HashMap;

public class Neat {

    public final static int MAX_NODES = (int)Math.pow(2,20);

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

    public static void main(String[] args){
        Neat neat = new Neat(3,3,3);
        Genome g = neat.empty_genome();
        System.out.println(g.getNodes().size());
    }
}
