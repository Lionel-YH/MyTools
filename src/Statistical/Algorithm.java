package Statistical;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {

    public Double calAverage(List<Double> array){
        return Arith.div(sum(array),array.size());
    }

    public Double calMedian(List<Double> array){
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

    public Double calStandardVar(List<Double> array){

        Double var = calVariance(array);
        return Math.sqrt(var);
    }

    public Double calQuantile3(List<Double> array){
        int size = array.size();
        int odds1 = size%4;
        int odds2 = (size-1)%4;
        int odd3 = size%2;
        if(0==odds1){
            int index = size/4*3;
            return (Arith.add(array.get(index),array.get(index-1)))/2;
        }else if(0==odds2){
            int index = (size-1)/4*3+1;
            return (Arith.add(array.get(index),array.get(index-1)))/2;
        }else if (0==odd3){
            int index = size/4+size/2+1;
            return (array.get(index-1));
        }else{
            int index = (size-1)/4+size/2+2;
            return (array.get(index-1));
        }
    }

    public Double calQuantile1(List<Double> array){
        int size = array.size();
        int odds1 = size%4;
        int odds2 = (size-1)%4;
        int odd3 = size%2;
        if(0==odds1||0==odds2){
            int index = size/4;
            return (Arith.add(array.get(index),array.get(index-1)))/2;
        }else {
            int index = size/4+1;
            return (array.get(index-1));
        }
    }

    public Double calIQR(List<Double> array){
        return Arith.sub(calQuantile3(array),calQuantile1(array));
    }

    public Double calNIQR(List<Double> array){
        return Arith.mul(0.7413,calIQR(array));
    }

    public Double calMADe(List<Double> array){
        Double median = calMedian(array);
        List<Double> d = new ArrayList<>();
        for(Double num:array){
            d.add(Math.abs(Arith.sub(num,median)));
        }
        return Arith.mul(1.483,calMedian(d));
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
        array.add(6.6);
        Algorithm ag = new Algorithm();
        System.out.println(array);
        Collections.sort(array);
        System.out.println(array);
        System.out.println(ag.calAverage(array));
        System.out.println(ag.calMedian(array));
        System.out.println(ag.calVariance(array));
        System.out.println(ag.calStandardVar(array));
        System.out.println(ag.calQuantile3(array));
        System.out.println(ag.calQuantile1(array));
        System.out.println(ag.calIQR(array));
        System.out.println(ag.calNIQR(array));
        System.out.println(ag.calMADe(array));
    }
}
