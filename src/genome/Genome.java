package genome;

import neat.Neat;
import data_structures.RandomHashSet;

public class Genome {

    private RandomHashSet<ConnectionGene> connections = new RandomHashSet<>();
    private RandomHashSet<NodeGene> nodes = new RandomHashSet<>();

    private Neat neat;

    public Genome(Neat neat){
        this.neat = neat;
    }

    public double distance(Genome g2){
        Genome g1 = this;

        int highest_innovation_number_gene1 = g1.getConnections().get(g1.getConnections().size()-1).getInnovation_number();
        int highest_innovation_number_gene2 = g2.getConnections().get(g2.getConnections().size()-1).getInnovation_number();

        if(highest_innovation_number_gene1<highest_innovation_number_gene2){
            Genome temp = g1;
            g1 = g2;
            g2 = temp;
        }

        int index_g1=0,index_g2=0;

        int disjoint=0,excess=0,similar=0;
        double weight_diff = 0;

        while(index_g1 < g1.getConnections().size() && index_g2 < g2.getConnections().size()){
            ConnectionGene gene1 = g1.getConnections().get(index_g1);
            ConnectionGene gene2 = g1.getConnections().get(index_g2);

            int in1 = gene1.getInnovation_number();
            int in2 = gene2.getInnovation_number();

            if(in1==in2) {
                similar++;
                weight_diff += Math.abs(gene1.getWeight() - gene2.getWeight());
                index_g1++;
                index_g2++;
            }else if(in1>in2){
                disjoint++;
                index_g2++;
            }
            else{
                disjoint++;
                index_g1++;
            }
        }
        weight_diff /= similar;
        excess = g1.getConnections().size()-index_g1;

        double N = Math.max(g1.getConnections().size(),g2.getConnections().size());
        if(N<20) N = 1;

        return neat.getC1() * disjoint/N + neat.getC2()*excess/N + neat.getC3()*weight_diff;
    }

    public static Genome crossover(Genome g1,Genome g2){

        Neat neat = g1.getNeat();
        Genome genome = neat.empty_genome();

        int index_g1=0,index_g2=0;

        while(index_g1 < g1.getConnections().size() && index_g2 < g2.getConnections().size()){
            ConnectionGene gene1 = g1.getConnections().get(index_g1);
            ConnectionGene gene2 = g1.getConnections().get(index_g2);

            int in1 = gene1.getInnovation_number();
            int in2 = gene2.getInnovation_number();

            if(in1==in2) {

                if(Math.random()>0.5){
                    genome.getConnections().add(neat.getConnection(gene1));
                }else{
                    genome.getConnections().add(neat.getConnection(gene2));
                }

                index_g1++;
                index_g2++;
            }else if(in1>in2){
                index_g2++;
            }
            else{
                genome.getConnections().add(neat.getConnection(gene1));
                index_g1++;
            }
        }

        while (index_g1<g1.getConnections().size()){
            ConnectionGene gene1 = g1.getConnections().get(index_g1);
            genome.getConnections().add(neat.getConnection(gene1));
            index_g1++;
        }

        for(ConnectionGene c:genome.getConnections().getData()){
            genome.getNodes().add(c.getFrom());
            genome.getNodes().add(c.getTo());
        }

        return genome;
    }

    public RandomHashSet<ConnectionGene> getConnections() {
        return connections;
    }

    public RandomHashSet<NodeGene> getNodes() {
        return nodes;
    }

    public Neat getNeat() {
        return neat;
    }
}
