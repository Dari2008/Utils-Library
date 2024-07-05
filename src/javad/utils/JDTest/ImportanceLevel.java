package javad.utils.JDTest;

public enum ImportanceLevel {
	Importance_Level_1, Importance_Level_2, Importance_Level_3, Importance_Level_4, Importance_Level_5, Importance_Level_6, Importance_Level_7, Importance_Level_8, Importance_Level_9, Importance_Level_10, Importance_Level_11;

	public ImportanceLevel getByNumber(int number) {
		return valueOf("Importance_Level_" + number);
	}

}
