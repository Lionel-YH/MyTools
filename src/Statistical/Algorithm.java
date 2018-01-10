package Statistical;

import javafx.util.Pair;

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
        return array.length%2==1?array[size/2]:Arith.div((array[size/2]+array[size/2+1]),2);
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
        double s_ = pair.getValue();
        double uncertain =  Arith.mul(1.25,Arith.div(s_,Math.sqrt(size)));
        return new Pair<>(1.1,uncertain);
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
        double[][] matrix = {{1.2,8.1,3.1,3.6,5.4,6.1,7.4,8.4,3.3,1.8,6.6}};
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
        System.out.println("confirm:"+ag.confirm(array,matrix[0],1.454,2.067));
        System.out.println("=========");
        System.out.println(11/2);
    }
}
