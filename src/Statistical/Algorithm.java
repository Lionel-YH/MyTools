package Statistical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {

    public Double calAverage(List<Double> array){
        return Arith.div(sum(array),array.size());
    }

    public Double calMedian(List<Double> array){
        Collections.sort(array);
        return array.size()%2==1?array.get(array.size()/2):Arith.div((array.get(array.size()/2)+array.get(array.size()/2+1)),2);
    }

    public Double calVariance(List<Double> array){
        List<Double> es = new ArrayList<>();
        Double mean = calAverage(array);
        for (Double num:array){
            Double error = Arith.sub(num,mean);
            es.add(Arith.mul(error,error));
        }
        return Arith.div(sum(es),array.size());
    }

    public Double sum(List<Double> array){
        double result = 0.0;
        for(Double num:array){
            result = Arith.add(result,num);
        }
        return result;
    }

    public static void main(String[] args){
        List<Double> array = new ArrayList<>();
        array.add(1.1);
        array.add(1.2);
        array.add(2.1);
        array.add(3.1);
        array.add(3.6);
        array.add(5.6);
        array.add(6.1);
        array.add(7.4);
        array.add(8.4);
        array.add(9.0);
        array.add(1.8);
        Algorithm ag = new Algorithm();
        System.out.println(array);
        System.out.println(ag.calAverage(array));
        System.out.println(ag.calMedian(array));
        System.out.println(ag.calVariance(array));

    }
}
