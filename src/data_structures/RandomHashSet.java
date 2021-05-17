package data_structures;

import java.util.ArrayList;
import java.util.HashSet;

public class RandomHashSet<T> {

    HashSet<T> set;
    ArrayList<T> data;

    public RandomHashSet(){
        this.set = new HashSet<>();
        this.data = new ArrayList<>();
    }

    public boolean contains(T object){
        return set.contains(object);
    }

    public T random_element(){
        if(data.size()>0)
        return data.get((int)(Math.random()* data.size()));
        else return null;
    }

    public int size(){
        return data.size();
    }

    public void add(T object){
        if(!set.contains(object)){
            set.add(object);
            data.add(object);
        }
    }

    public void clear(){
        set.clear();
        data.clear();
    }

    public T get(int index){
        if (index<0 || index>=data.size()) return null;
        else return data.get(index);
    }

    public void remove(int index){
        if (index<0 || index>=data.size()) return;
        set.remove(data.get(index));
        data.remove(index);
    }

    public void remove(T object){
        if (!contains(object)) return;
        set.remove(object);
        data.remove(object);
    }
}
