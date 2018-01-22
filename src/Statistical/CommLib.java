package Statistical;

public class CommLib {

    //获取数组之和
    public double getArraySum(double[] data) {
        double sum = 0.00000;
        if(data.length == 0) return sum;
        for(int i = 0; i<data.length;i++)
            sum += data[i];
        return sum;
    }

    //获取数组均值
    public double getArrayAvg(double[] data) {
        if(data.length == 0) return 0;
        return getArraySum(data)/data.length;
    }

    //获取总平均值
    public double getTotalAvg(double[][] data) {
        if(data.length == 0) return 0;
        double totalSum = 0.0000;
        for(int i = 0; i<data.length; i++) {
            totalSum += getArrayAvg(data[i]);
        }
        return totalSum/data.length;

    }

    //获取二维数组均值
    public double gettwoDemensionArrayAvg(double[][] data) {
        if(data.length == 0) return 0;
        double sum = 0.00000;
        for(int i = 0; i< data.length; i++) {
            for(int j = 0; j < data[i].length;j++) {
                sum += data[i][j];
            }
        }

        return sum / (data.length * data[0].length);
    }

    //求方差
    public double getVariance(double[] data) {
        if(data.length == 0) return 0;
        double variance = 0.0000;
        double avg = getArrayAvg(data);
        double temp = 0.0000;
        for(int i = 0; i<data.length; i++) {
            temp += Math.pow(data[i]-avg, 2);
        }
        variance = temp / data.length;

        return variance;
    }

    //求样本均值的方差
    public double getAvgVariance(double[] avgs, double total_avg) {
        double avg_variance = 0.0000;
        double temp = 0.0000;
        for(int i = 0; i < avgs.length; i++) {
            temp = Math.pow(avgs[i] - total_avg,2);
        }
        avg_variance = temp / (avgs.length-1);
        return avg_variance;

    }

    //求标准差
    public double getStdDeviation(double[] data) {

        double avg = getArrayAvg(data);
        double temp = 0;
        for(int i = 0; i < data.length;i++) {
            temp += Math.pow(data[i]-avg, 2);
        }
        double stdvariation = Math.sqrt(temp/data.length);
        return stdvariation;
    }

}
