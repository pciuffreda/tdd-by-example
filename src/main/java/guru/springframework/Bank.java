package guru.springframework;

import java.util.HashMap;

public class Bank {

    private final HashMap<Pair, Integer> rateMap = new HashMap<>();

    public Money reduce(Expression source, String currency){
        return source.reduce(this, currency);
    }

    public int rate(String from, String to) {
        Integer rate;
        if(from.equals(to)){
            rate = 1;
        } else {
            rate = rateMap.get(new Pair(from, to));
        }
        return rate;
    }

    public void addRate(String from, String to, int rate) {
        rateMap.put(new Pair(from, to), rate);
    }
}
