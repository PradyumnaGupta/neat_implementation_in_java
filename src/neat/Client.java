package neat;

import calculator.Calculator;
import genome.Genome;

public class Client {

    public Calculator calculator;

    private Genome genome;
    public double score;
    public Species species;

    public double distance(Client other){
        return this.genome.distance(other.getGenome());
    }

    public void mutate(){
        genome.mutate();
    }

    public void generate_calculator(){
        this.calculator = new Calculator(genome);
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public double[] calculate(double... inputs){
        if(calculator == null)
            this.generate_calculator();
        return calculator.calculate(inputs);
    }


}
