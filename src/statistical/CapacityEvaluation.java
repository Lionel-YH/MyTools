package statistical;


public class CapacityEvaluation {

	//6.1 检查指定值不确定度
	public String checkSpecialValUncertainty(double xpt, double pt) {
		if (xpt > 0.3 * pt) return "该指定值的不确定度不可忽略，请使用z'值、Zeta值或En值";
		if (xpt < 0.3 * pt) return "该指定值的不确定度可忽略";
		return "";
	}

	//6.2 偏倚

}

