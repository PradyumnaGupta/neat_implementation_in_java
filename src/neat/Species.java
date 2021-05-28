package neat;

import data_structures.RandomHashSet;
import genome.Genome;

import java.util.Comparator;

public class Species {
    public RandomHashSet<Client> clients = new RandomHashSet<>();
    public Client representative;
    public double score;

    public Species(Client representative){
        this.representative = representative;
        this.representative.setSpecies(this);
        clients.add(representative);
    }

    public boolean put(Client client){
        if(client.distance(representative) < client.getGenome().getNeat().getCP()){
            client.setSpecies(this);
            this.clients.add(client);
            return true;
        }
        else return false;
    }

    public void force_put(Client client){
        client.setSpecies(this);
        this.clients.add(client);
    }

    public void evaluate_score(){
        double v = 0;
        for(Client c:clients.getData()){
            v+=c.getScore();
        }
        score = v / clients.size();
    }

    public void reset(){
        representative = clients.random_element();
        for(Client c:clients.getData())
            c.setSpecies(null);
        clients.clear();

        clients.add(representative);
        representative.setSpecies(this);
        score =0;
    }

    public void kill(double percentage){
        clients.getData().sort(
                new Comparator<Client>() {
                    @Override
                    public int compare(Client o1, Client o2) {
                        return Double.compare(o1.getScore(),o2.getScore());
                    }
                }
        );

        double amount = percentage * clients.size();
        for(int i=0;i<amount;i++){
            clients.get(0).setSpecies(null);
            clients.remove(0);
        }
    }

    public Genome breed(){
        Client c1 = clients.random_element();
        Client c2 = clients.random_element();

        if(c1.getScore() > c2.getScore()) return Genome.crossover(c1.getGenome(),c2.getGenome());
        else return Genome.crossover(c2.getGenome(),c1.getGenome());
    }

    public void goExtinct(){
        for(Client c:clients.getData())
            c.setSpecies(null);
    }

    public int size(){
        return clients.size();
    }

    public RandomHashSet<Client> getClients() {
        return clients;
    }

    public Client getRepresentative() {
        return representative;
    }

    public double getScore() {
        return score;
    }
}
