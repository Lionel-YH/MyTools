package Statistical;

import javafx.util.Pair;
import org.python.icu.text.ArabicShaping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Algorithm {

    public double calAverage(double[] array){
        return Arith.div(sum(array),array.length);
    }

    public double calMedian(double[] array){
        int size = array.length;
        quickSort(array,0,size-1);
        return array.length%2==1?array[size/2]:Arith.div((array[size/2]+array[size/2-1]),2);
    }

    public double calVariance(double[] array){
        double mean = calAverage(array);
        return Arith.div(calErrorSquare(array,mean),array.length);
    }

    public double calErrorSquare(double[] array,double mid){
        double result = 0.0;
        for(double num:array){
            double error = Arith.sub(num,mid);
            result=Arith.add(result,Arith.mul(error,error));
        }
        return result;
    }

    public double calStandardVar(double[] array){

        double var = calVariance(array);
        return Math.sqrt(var);
    }

    public double calQuantile3(double[] array){
        int size = array.length;
        quickSort(array,0,size-1);
        int odds1 = size%4;
        int odds2 = (size-1)%4;
        int odd3 = size%2;
        if(0==odds1){
            int index = size/4*3;
            return (Arith.add(array[index],array[index-1]))/2;
        }else if(0==odds2){
            int index = (size-1)/4*3+1;
            return (Arith.add(array[index],array[index-1]))/2;
        }else if (0==odd3){
            int index = size/4+size/2+1;
            return (array[index-1]);
        }else{
            int index = (size-1)/4+size/2+2;
            return (array[index-1]);
        }
    }

    public double calQuantile1(double[] array){
        int size = array.length;
        quickSort(array,0,size-1);
        int odds1 = size%4;
        int odds2 = (size-1)%4;
        if(0==odds1||0==odds2){
            int index = size/4;
            return (Arith.add(array[index],array[index-1]))/2;
        }else {
            int index = size/4+1;
            return (array[index-1]);
        }
    }

    public double calIQR(double[] array){
        return Arith.sub(calQuantile3(array),calQuantile1(array));
    }

    public double calNIQR(double[] array){
        return Arith.mul(0.7413,calIQR(array));
    }

    public double calMADe(double[] array){
        int size = array.length;
        double median = calMedian(array);
        double[] d = new double[size];
        for(int i=0;i<size;i++){
            d[i]=Math.abs(Arith.sub(array[i],median));
        }
        quickSort(d,0,size-1);
        return Arith.mul(1.483,calMedian(d));
    }

    public Pair<Double, Double> methodA(double[] array){
        double x_ = calMedian(array);
        double s_ = calMADe(array);
        int size = array.length;
        while(true){
            double delta = Arith.mul(1.5,s_);
            for(int i=0;i<size;i++){
                if (Arith.sub(array[i],Arith.sub(x_,delta))<0){
                    array[i]=Arith.sub(x_,delta);
                }else if(Arith.sub(array[i],Arith.add(x_,delta))>0){
                    array[i]=Arith.add(x_,delta);
                }
            }
            double newX_ = Arith.div(sum(array),size);
            double newS_ = Arith.mul(1.134,Math.sqrt(Arith.div(calErrorSquare(array,newX_),(size - 1))));
            if((Arith.sub(newX_,x_)==0.0)&&((int)Arith.mul(1000,newS_)==(int)Arith.mul(1000,s_))){
                break;
            }else {
                x_ = newX_;
                s_ = newS_;
            }
        }
        return new Pair<>(x_,s_);
    }

    public double methodS(double[][] matrix){
        double[][] v = {{1.645,1.097},{1.517,1.054},{1.444,1.039},{1.395,1.032},{1.359,1.027},{1.332,1.024},{1.310,1.021},{1.292,1.019},{1.277,1.018},{1.264,1.017}};
        int vR = matrix.length;
        int vC = matrix[0].length;
        double eta = v[vC-2][0];
        double xi =  v[vC-2][1];
        double[] w = new double[vR];
        for(int i=0;i<vR;i++){
            w[i]=calStandardVar(matrix[i]);
        }
        double w_ = calMedian(w);
        while(true){
            double psi = Arith.mul(eta,w_);
            for(int j=0;j<vR;j++){
                if(Arith.sub(w[j],psi)>0){
                    w[j]=psi;
                }
            }
            double newW_ = Arith.mul(xi,Math.sqrt(Arith.div(calErrorSquare(w,0.0),vR)));
            if ((int)Arith.mul(1000,newW_)==(int)Arith.mul(1000,w_)){
                break;
            }else{
                w_=newW_;
            }
        }
        return w_;
    }

    public double methodQn(double[] array){
        int size=array.length;
        int p = size;
        System.out.println("array的长度："+size);
        double[] d = new double[p*(p-1)/2];
        List<Double> dd = new ArrayList<>();
        for(int i=0;i<p-1;i++){
            for(int j=i+1;j<p;j++){
                dd.add(Math.abs(Arith.sub(array[i],array[j])));
            }
        }
        for(int k=0;k<dd.size();k++){
            d[k]=dd.get(k);
        }
        quickSort(d,0,d.length-1);
        for(double num:d){
            System.out.print(num);
            System.out.print(",");
        }
        System.out.println();
        System.out.println("d的长度："+d.length);
        int h = p/2;
        int k = h*(h-1)/2;
        double[] b = {0.9937,0.9937,0.5132,0.8440,0.6122,0.8588,0.6699,0.8734,0.7201,0.8891,0.7574};
        double bp;
        if(p > 12){
            double rp;
            if(p%2==1){
                rp = Arith.div(Arith.add(1.6019,Arith.div(Arith.sub(-2.128,Arith.div(5.172,p)),p)),p);
            }
            else{
                rp = Arith.div(Arith.add(3.6756,Arith.div(Arith.add(1.965,Arith.div(Arith.sub(6.978,Arith.div(77,p)),p)),p)),p);
            }
            bp = Arith.div(1,Arith.add(rp,1));
        } else{
            bp = b[p-2];
        }
        double Qn = Arith.mul(2.2219,Arith.mul(d[k],bp));
        return Qn;
    }

    public Pair<Double,Double> confirm(double[] arrayX,double[] arrayY,double x_crm,double u_crm) {
        int size = arrayX.length;
        double[] d = new double[size];
        for(int i=0;i<size;i++){
            d[i]=Arith.sub(arrayX[i],arrayY[i]);
        }
        double d_mean=Arith.div(sum(d),size);
        double x_pt = Arith.add(x_crm,d_mean);
        double s2d = Arith.div(calErrorSquare(d,d_mean),Math.sqrt(size));
        double u2d = Arith.div(s2d,size-1);
        double u_char = Math.sqrt(Arith.add(Arith.mul(u_crm,u_crm),Arith.mul(u2d,u2d)));
        return new Pair<>(x_pt,u_char);
    }

    public Pair<Double,Double> confirm(double[] array) {
        Pair<Double,Double> pair = methodA(array);
        int size = array.length;
        double x_pt = pair.getKey();
        double s_ = pair.getValue();
        double uncertain =  Arith.mul(1.25,Arith.div(s_,Math.sqrt(size)));
        return new Pair<>(x_pt,uncertain);
    }

    public double hampel(double[][] matrix) {
        int size = matrix.length;
        double S = calculateS(matrix);
        double[] data_y = new double[size];
        for(int i=0;i<size;i++){
            double[] lab_i = matrix[i];
            data_y[i] = Arith.div(sum(lab_i),lab_i.length);
        }
        List<Double> data_d = new ArrayList<>();
        List<Double> result = new ArrayList<>();
        for(double d:data_y){
            data_d.add(Arith.sub(d,Arith.mul(4.5,S)));
            data_d.add(Arith.sub(d,Arith.mul(3,S)));
            data_d.add(Arith.sub(d,Arith.mul(1.5,S)));
            data_d.add(Arith.add(d,Arith.mul(4.5,S)));
            data_d.add(Arith.add(d,Arith.mul(3,S)));
            data_d.add(Arith.add(d,Arith.mul(1.5,S)));
        }
        Collections.sort(data_d);
        for(int i=0;i<data_d.size()-1;i++){
            double[] tmp1 = new double[data_y.length];
            double[] tmp2 = new double[data_y.length];
            for(int j=0;j<data_y.length;j++){
                tmp1[j]=psi(Arith.div(Arith.sub(data_y[j],data_d.get(i)),S));
                tmp2[j]=psi(Arith.div(Arith.sub(data_y[j],data_d.get(i+1)),S));
            }
            double psi_sum = sum(tmp1);
            double psi_sum_next = sum(tmp2);
            if(psi_sum==0){
                result.add(data_d.get(i));
            }else if(Arith.mul(psi_sum,psi_sum_next)<0){
                result.add(Arith.sub(data_d.get(i),Arith.div(Arith.mul(psi_sum,Arith.sub(data_d.get(i+1),data_d.get(i))),Arith.sub(psi_sum_next,psi_sum))));
            }
            if(i==data_d.size()-2&&psi_sum_next==0){
                result.add(data_d.get(i+1));
            }
        }
        quickSort(data_y,0,data_y.length-1);
        int l = data_y.length;
        for(double d :data_y){
            System.out.println(d);
        }
        double mid_y = calMedian(data_y);
        if(result.size()==0){
            return mid_y;
        }else{
            int counter = 0;
            double tmp_x = 0;
            for(double x:result){
                if(counter==0||tmp_x==x){
                    tmp_x = x;
                    counter =+ 1;
                }else if(Math.abs(Arith.sub(tmp_x,mid_y))>Math.abs(Arith.sub(x,mid_y))){
                    tmp_x = x;
                    counter = 1;
                }
            }
            if(counter ==1){
                return tmp_x;
            }else{
                return mid_y;
            }
        }
    }

    public double psi(double q) {
        if(q<=-4.5){
            return 0;
        }else if(q>-4.5&&q<=-3){
            return Arith.sub(-4.5,q);
        }else if(q>-3&&q<=-1.5){
            return -1.5;
        }else if(q>-1.5&&q<=1.5){
            return q;
        }else if(q>1.5&&q<=3){
            return 1.5;
        }else if(q>3&&q<=4.5){
            return Arith.sub(4.5,q);
        }else{
            return 0;
        }
    }

    public double calculateS(double[][] matrix) {
        List<Double> x_sorted_list = new ArrayList<>();
        for(int j=0;j<matrix.length;j++){
            double[] lab_j = matrix[j];
            for(int i=0;i<j;i++){
                double[] lab_i = matrix[i];
                for(int k=0;k<lab_i.length;k++){
                    double ik = lab_i[k];
                    for(int m=0;m<lab_j.length;m++){
                        double jm = lab_j[m];
                        x_sorted_list.add(Math.abs(Arith.sub(ik,jm)));
                    }
                }
            }
        }
        Collections.sort(x_sorted_list);

        List<Double> G_function_value = new ArrayList<>();
        for(int index=0;index<x_sorted_list.size();index++){
            double item = x_sorted_list.get(index);
            if(0==index&&item>0){
                G_function_value.add(Arith.mul(0.5,H1(item,matrix)));
            }else if(0==index&&item==0){
                G_function_value.add(0.0);
            }else{
                G_function_value.add(Arith.mul(0.5,Arith.add(H1(item,matrix),H1(x_sorted_list.get(index-1),matrix))));
            }
        }
        if(x_sorted_list.get(0)!=0.0){
            x_sorted_list.add(0,0.0);
            G_function_value.add(0,0.0);
        }

        double tmp1 = Arith.add(0.25,Arith.mul(0.75,H1(0,matrix)));
        double tmp2 = Arith.add(0.625,Arith.mul(0.375,H1(0,matrix)));
        int i = 0;
        while(i<G_function_value.size()&&tmp1>G_function_value.get(i)){
            i += 1;
        }
        if(i==G_function_value.size()){
            System.out.println("err1");
            System.exit(1);
        }
        double value1 = Arith.add(Arith.mul(Arith.div(Arith.sub(x_sorted_list.get(i),x_sorted_list.get(i-1)),Arith.sub(G_function_value.get(i),G_function_value.get(i-1))),
                                    Arith.sub(tmp1,G_function_value.get(i-1))),x_sorted_list.get(i-1));
        double value2 = NormalDistributionInverseFunction(tmp2);
        return Arith.div(value1,Arith.mul(Math.sqrt(2),value2));
    }

    public double H1(double x,double[][] matrix) {
        int p = matrix.length;
        double tmp1 = 0.0;
        for(int j=0;j<p;j++){
            double[] lab_j = matrix[j];
            for(int i=0;i<j;i++){
                double[] lab_i = matrix[i];
                int len_i = lab_i.length;
                int len_j = lab_j.length;
                double tmp2 = 0.0;
                for(double k:lab_i){
                    for(double m:lab_j){
                        if(Arith.sub(x,Math.abs(Arith.sub(k,m)))>=0){
                            tmp2 += 1;
                        }
                    }
                }
                tmp1 = Arith.add(tmp1,Arith.div(tmp2,Arith.mul(len_i,len_j)));
            }
        }
        return Arith.mul(tmp1,Arith.div(2,(p-1)*p));
    }

    public double NormalDistributionInverseFunction(double value) {
        double[] data =
       {0.5000,0.5040,0.5080,0.5120,0.5160,0.5199,0.5239,0.5279,0.5319,0.5359,
        0.5398,0.5438,0.5478,0.5517,0.5557,0.5596,0.5636,0.5675,0.5714,0.5753,
        0.5793,0.5832,0.5871,0.5910,0.5948,0.5987,0.6026,0.6064,0.6103,0.6141,
        0.6179,0.6217,0.6255,0.6293,0.6331,0.6368,0.6404,0.6443,0.6480,0.6517,
        0.6554,0.6591,0.6628,0.6664,0.6700,0.6736,0.6772,0.6808,0.6844,0.6879,
        0.6915,0.6950,0.6985,0.7019,0.7054,0.7088,0.7123,0.7157,0.7190,0.7224,
        0.7257,0.7291,0.7324,0.7357,0.7389,0.7422,0.7454,0.7486,0.7517,0.7549,
        0.7580,0.7611,0.7642,0.7673,0.7703,0.7734,0.7764,0.7794,0.7823,0.7852,
        0.7881,0.7910,0.7939,0.7967,0.7995,0.8023,0.8051,0.8078,0.8106,0.8133,
        0.8159,0.8186,0.8212,0.8238,0.8264,0.8289,0.8355,0.8340,0.8365,0.8389,
        0.8413,0.8438,0.8461,0.8485,0.8508,0.8531,0.8554,0.8577,0.8599,0.8621,
        0.8643,0.8665,0.8686,0.8708,0.8729,0.8749,0.8770,0.8790,0.8810,0.8830,
        0.8849,0.8869,0.8888,0.8907,0.8925,0.8944,0.8962,0.8980,0.8997,0.9015,
        0.9032,0.9049,0.9066,0.9082,0.9099,0.9115,0.9131,0.9147,0.9162,0.9177,
        0.9192,0.9207,0.9222,0.9236,0.9251,0.9265,0.9279,0.9292,0.9306,0.9319,
        0.9332,0.9345,0.9357,0.9370,0.9382,0.9394,0.9406,0.9418,0.9430,0.9441,
        0.9452,0.9463,0.9474,0.9484,0.9495,0.9505,0.9515,0.9525,0.9535,0.9535,
        0.9554,0.9564,0.9573,0.9582,0.9591,0.9599,0.9608,0.9616,0.9625,0.9633,
        0.9641,0.9648,0.9656,0.9664,0.9672,0.9678,0.9686,0.9693,0.9700,0.9706,
        0.9713,0.9719,0.9726,0.9732,0.9738,0.9744,0.9750,0.9756,0.9762,0.9767,
        0.9772,0.9778,0.9783,0.9788,0.9793,0.9798,0.9803,0.9808,0.9812,0.9817,
        0.9821,0.9826,0.9830,0.9834,0.9838,0.9842,0.9846,0.9850,0.9854,0.9857,
        0.9861,0.9864,0.9868,0.9871,0.9874,0.9878,0.9881,0.9884,0.9887,0.9890,
        0.9893,0.9896,0.9898,0.9901,0.9904,0.9906,0.9909,0.9911,0.9913,0.9916,
        0.9918,0.9920,0.9922,0.9925,0.9927,0.9929,0.9931,0.9932,0.9934,0.9936,
        0.9938,0.9940,0.9941,0.9943,0.9945,0.9946,0.9948,0.9949,0.9951,0.9952,
        0.9953,0.9955,0.9956,0.9957,0.9959,0.9960,0.9961,0.9962,0.9963,0.9964,
        0.9965,0.9966,0.9967,0.9968,0.9969,0.9970,0.9971,0.9972,0.9973,0.9974,
        0.9974,0.9975,0.9976,0.9977,0.9977,0.9978,0.9979,0.9979,0.9980,0.9981,
        0.9981,0.9982,0.9982,0.9983,0.9984,0.9984,0.9985,0.9985,0.9986,0.9986,
        0.9987,0.9990,0.9993,0.9995,0.9997,0.9998,0.9998,0.9999,0.9999,1.0000};
        double s = value;
        if(s<0.5000){
            s = Arith.sub(1,s);
        }
        int i = 0;
        while(i<310&&data[i]<s){
            i += 1;
        }
        if(i==0){
            return 0;
        }else{
            double x;
            if(i>=301){
                x = Arith.add(Arith.mul(Arith.div(0.1,Arith.sub(data[i],data[i-1])),Arith.sub(s,data[i-1])),data[i-1]);
            }else{
                x = Arith.add(Arith.mul(Arith.div(0.01,Arith.sub(data[i],data[i-1])),Arith.sub(s,data[i-1])),data[i-1]);
            }
            if(value<0.5000){
                return Arith.mul(x,-1);
            }else {
                return x;
            }
        }
    }


    public double sum(double[] array){
        double result = 0.0;
        for(double num:array){
            result = Arith.add(result,num);
        }
        return result;
    }

    private void quickSort(double[] array,int start,int end){
        if(start>=end){
            return;
        }
        if(end-start==1){
            if(Arith.sub(array[start],array[end])>0){
                swap(array,start,end);
            }
            return;
        }
        double pivot = array[start];
        int head=start+1;
        int rear=end;
        while(head<rear){
            while(head<rear){
                if(Arith.sub(array[head],pivot)<0){
                    head++;
                }else{
                    break;
                }
            }
            while(rear>=head){
                if(Arith.sub(array[rear],pivot)>=0){
                    rear--;
                }else{
                    break;
                }
            }
            if(head<rear){
                swap(array,head,rear);
            }
        }
        swap(array,start,rear);
        quickSort(array,start,rear-1);
        quickSort(array,rear+1,end);
    }

    private void swap(double[] array,int first,int second){
        double tmp=array[first];
        array[first]=array[second];
        array[second]=tmp;
    }

    public static void main(String[] args){
        double[] array = {1.2,10.1,3.1,3.6,5.4,6.1,7.4,8.4,3.3,1.8,6.6};
        double[][] matrix = {{1.2,8.1,3.1,3.6,5.4,6.1,7.4,8.4,3.3,1.8,6.6},{3.2,5.1,3.1,6.6,5.4,6.1,6.4,8.4,4.3,2.8,5.6}};
        Algorithm ag = new Algorithm();
        ag.quickSort(array,0,array.length-1);
        for(double d:array) {
            System.out.print(d);
            System.out.print(",");
        }
        System.out.println();
//        System.out.println("calAverage:"+ag.calAverage(array));
//        System.out.println("calMedian:"+ag.calMedian(array));
//        System.out.println("calVariance:"+ag.calVariance(array));
//        System.out.println("calStandardVar:"+ag.calStandardVar(array));
//        System.out.println("calQuantile3:"+ag.calQuantile3(array));
//        System.out.println("calQuantile1:"+ag.calQuantile1(array));
//        System.out.println("calIQR:"+ag.calIQR(array));
//        System.out.println("calNIQR:"+ag.calNIQR(array));
//        System.out.println("calMADe:"+ag.calMADe(array));
//        System.out.println("methodA:"+ag.methodA(array));
//        System.out.println("methodS:"+ag.methodS(matrix));
//        System.out.println("methodQn:"+ag.methodQn(array));
//        System.out.println("confirm:"+ag.confirm(array,matrix[0],1.454,2.067));
//        System.out.println("confirm:"+ag.confirm(array));
        System.out.println("hampel:"+ag.hampel(matrix));
        System.out.println("=========");
        System.out.println(11/2);
    }
}
