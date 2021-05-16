package data_structures;

import java.util.ArrayList;;

public class RandomSelector<T> {

    private ArrayList<T> objects;
    private ArrayList<Double> scores;

    private double total_score = 0;

    public void add(T object, Double score){
        objects.add(object);
        scores.add(score);
        total_score+=score;
    }

    public T random(){
        double v = Math.random()*total_score;

        double c = 0;

        for(int i=0;i< objects.size(); i++){
            c+= scores.get(i);
            if (c>=v)
                return objects.get(i);
        }
        return null;
    }
}
