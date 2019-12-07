package statistical;


public class ConfirmStdDeviation {

	//5.2 由一般模型确定；输入浓度c
	public double confirmedByGeneralModel(double c) {
		if (c< 0 || c>1) return -99999; //输入无效
		double val_pt = 0;
		if(c < 1.2 * Math.pow(10, -7))
			val_pt = 0.22 * c;
		else if(c > 1.2 * Math.pow(10, 2) && c < 0.138)
			val_pt = 0.02 * Math.pow(c, 0.8495);
		else if(c>0.138)
			val_pt = 0.01 * Math.pow(c, 0.5);
		return val_pt;
	}

	//5.3 由测量方法重复性和再现性确定
	//sd_repeatability 重复性标准差，sd_reproducibility 再现性标准差，m整数值
	public double ConfirmedByMeasureMethod(double sd_repeatability, double sd_reproducibility, int m) {

		double val_pt= 0.0000;
		val_pt = Math.sqrt(Math.pow(sd_reproducibility, 2) - Math.pow(sd_repeatability, 2) * (1-1/m));

		return val_pt;
	}

	//5.4 由稳健方法确定

}

