package Statistical;

import java.util.ArrayList;

public class HomogeneityandStabilityCheck {

	public double[][] singleFactorVariableAnalysis(double[][] data) {
		int m = data.length; //样品数
		int n = data[0].length; //每个样品检测数
		int returnvallength = 6;
		returnvallength = (m>returnvallength) ? m : 6;
		double[][] returnval = new double[2][returnvallength];
		int f1 = m-1;
		int f2 = m * (n-1);
		double[] inner_avg = new double[m]; //样品内均值
		CommLib objcommlib = new CommLib();
		for(int i = 0; i<m;i++) {
			inner_avg[i] = objcommlib.getArrayAvg(data[i]);
		}

		double outer_avg = objcommlib.gettwoDemensionArrayAvg(data); //样品间均值
		double outer_variance_temp = 0;
		for(int i = 0; i< m; i++) {
			outer_variance_temp += Math.pow(inner_avg[i]-outer_avg,2);
		}
		double outer_variance = n*outer_variance_temp/f1; //样品间方差
		double inner_variance_temp = 0;
		for(int i = 0; i<m; i++) {
			for(int j = 0; j<n; j++) {
				inner_variance_temp += Math.pow(data[i][j] - inner_avg[i],2);
			}
		}
		double inner_variance = inner_variance_temp / f2; //样品内方差

		double F = outer_variance /inner_variance;

		CallPyforStats objpy = new CallPyforStats();
		double fa = objpy.getFAlphaQuantile("", 0.05, f1, f2);
		//比较F与Fa的结果
		returnval[0] = inner_avg;
		returnval[1][0] = outer_avg;
		returnval[1][1] = inner_variance;
		returnval[1][2] = outer_variance;
		returnval[1][3] = f1;
		returnval[1][4] = f2;
		returnval[1][5] = F;
		return returnval;

	}

	//3.1.2 ss<=0.3*σ准则; returnval[0] = 样本间方差, returnval[1] = 样本内标准差,
	public double[] ssLessLaw(double[][] data) {
		int t = data.length; //样品个数
		int m = data[0].length; //每个样品测量次数
		double[] returnval = new double[2];
		double[] inner_avg = new double[t]; //样品内均值
		CommLib objcommlib = new CommLib();
		for(int i = 0; i<t;i++) {
			inner_avg[i] = objcommlib.getArrayAvg(data[i]);
		}
		double total_avg = objcommlib.getTotalAvg(data); //总体平均值
		double sample_avg_variance = objcommlib.getAvgVariance(inner_avg,total_avg); //样本均值的方差
		double[] innerSampleVariance = testSampleVariance(data,inner_avg,total_avg); //测试份量间方差
		double inner_sample_variance = objcommlib.getArrayAvg(innerSampleVariance); //样本内方差
		double inner_sample_stdvariance = Math.sqrt(inner_sample_variance/2); //样本内标准差
		double sample_between_variance = sample_avg_variance - inner_sample_variance/m; //样本间方差
		returnval[0] = sample_between_variance;
		returnval[1] = inner_sample_stdvariance;

		return returnval;
	}

	//求测试样本间方差
	public double[] testSampleVariance(double[][] data, double[] inner_avg,double totalAvg) {
		double[] variances = new double[data.length];
		double temp = 0.0000;
		for(int i = 0; i< data.length; i++) {

			for(int j = 0; j<data[0].length; j++) {
				temp += Math.pow(data[i][j] - inner_avg[i], 2);
			}
			variances[i] = temp / ( data[i].length -1);
		}

		return variances;
	}

	//扩展方法  returnval[0] = 样本间方差，returnval[1] = math.sqrt(c);
	public double[] extensionMethod(double[][] data, double stddeviation) {
		double[] returnval = new double[2];
		int g = data.length;
		double[] valssLessLaw = ssLessLaw(data);
		double sw = valssLessLaw[1]; //样本内标准差
		double allow_stddev = Math.pow(0.3 * stddeviation,2);
		double f1,f2;
		CallPyforStats  objCallPy = new CallPyforStats();
		f1 = objCallPy.getChi2AlphaQuantile("filePath", 0.95, g-1);
		f2 = (objCallPy.getFAlphaQuantile("filepath", 0.95, g-1, g) -1) /2;
		if(data[0].length>2) {
			f2 = objCallPy.getFAlphaQuantile("", 0.95, g-1, g*(data[0].length));
		}
		double c = f1 * allow_stddev + f2 * Math.pow(sw, 2);

		double outer_variance = valssLessLaw[0];
		returnval[0] = outer_variance;
		returnval[1] = Math.sqrt(c);
		return returnval;
	}

	//3.2.1 稳定性检验 |x-y|<=0.3σ}
	/*data_H 均匀性检验的数据；data_S稳定性检验的数据；能力评定标准差stddeviation; 返回两个数据的总体均值之差的绝对值*/
	public double absolutlyLessLaw(double[][] data_H, double[][] data_S) {
		CommLib objcommlib = new CommLib();
		double avg_H = objcommlib.getTotalAvg(data_H);
		double avg_S = objcommlib.getTotalAvg(data_S);


		return Math.abs(avg_S-avg_H);
	}

	//t检验法--与标准值/参考值比较
	/*data:一个实验室多个数据，u标准值/参考值,n1自由度*/
	public double[] tCheckMethod(double[] data, double u, int n1) {
		double[] returnval = new double[2];
		CommLib objcommlib = new CommLib();
		double avg = objcommlib.getArrayAvg(data);
		double n = data.length;
		double temp = 0.0000;
		for(int i = 0; i < n;i++) {
			temp += Math.pow(data[i]-avg, 2);
		}
		double stdvariation = Math.sqrt(temp/n);
		double t = Math.abs(avg-u)*Math.sqrt(n)/stdvariation;
		CallPyforStats objCallpy = new CallPyforStats();
		returnval[0] = stdvariation;
		returnval[1] = objCallpy.getTAlphaQuantile("", 0.95, n1-1);

		return returnval;
	}

	//t检验法-两个均值的比较 data_1稳定性检验前；data_2稳定性检验后
	public double[] tCheckComparison(double[] data_1, double[] data_2) {
		double[] returnval = new double[2];
		CommLib objcommlib = new CommLib();
		double stddev_data1 = objcommlib.getStdDeviation(data_1);
		double stddev_data2 = objcommlib.getStdDeviation(data_2);
		int n1 = data_1.length;
		int n2 = data_2.length;
		double avg1 = objcommlib.getArrayAvg(data_1);
		double avg2 = objcommlib.getArrayAvg(data_2);
		double temp1 = ((n1-1) * Math.pow(stddev_data1, 2) + (n2-1) * Math.pow(stddev_data2, 2)) / (n1 + n2 -2);
		double temp2 = (n1+n2)/n1*n2;
		double t = Math.abs(avg2-avg1) / Math.sqrt(temp1*temp2);
		CallPyforStats objCallpy = new CallPyforStats();
		returnval[0] = t;
		returnval[1] = objCallpy.getTAlphaQuantile("", 0.95, n1+n2-1);
		return  returnval;
	}




}