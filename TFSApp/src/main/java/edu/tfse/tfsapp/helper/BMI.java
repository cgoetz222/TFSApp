package edu.tfse.tfsapp.helper;

/**
 * The class calculates whether someone has overweight, normal weight, or underweight due to his BMI
 * @author carlo
 *
 */

public class BMI {
	/**
	 * returns the normal BMI range according to the sex, age of the current user (according to the American National Research Center
	 * @param sex (m ale; f emale; d iverse)
	 * @param age age of the user
	 * @return
	 */
	public static String getBMIRange(String sex, int age) {
		if(sex.equals("f")) {
			if(age <= 15) {
				return "17.7 - 23.7";
			} else if(age == 16) {
				return "18.1 - 24.2";
			} else if(age == 17) {
				return "18.3 - 24.7";
			} else if(age == 18) {
				return "18.5 - 24.9";
			} else if(age >= 19 && age <= 24) {
				return "18.0 - 23.0";
			} else if(age >= 25 && age <= 34) {
				return "19.0 - 24.0";
			} else if(age >= 35 && age <= 44) {
				return "20.0 - 25.0";
			} else if(age >= 45 && age <= 54) {
				return "21.0 - 26.0";
			} else if(age >= 55 && age <= 64) {
				return "22.0 - 27.0";
			} else if(age >= 65) {
				return "23.0 - 28.0";
			}
		} else {
			if(age <= 15) {
				return "17.6 - 22.8";
			} else if(age == 16) {
				return "18.2 - 23.7";
			} else if(age == 17) {
					return "18.7 - 24.4";
			} else if(age == 18) {
				return "19.2 - 25.0";
			} else if(age >= 19 && age <= 24) {
				return "19.0 - 24.0";
			} else if(age >= 25 && age <= 34) {
				return "20.0 - 25.0";
			} else if(age >= 35 && age <= 44) {
				return "21.0 - 26.0";
			} else if(age >= 45 && age <= 54) {
				return "22.0 - 27.0";
			} else if(age >= 55 && age <= 64) {
				return "23.0 - 28.0";
			} else if(age >= 65) {
				return "24.0 - 29.0";
			}
		}
		return "";
	}

	
	/**
	 * returns the status of according to the age, sex and bmi of the current user
	 * @param sex (m ale; f emale; d iverse)
	 * @param age age of the user
	 * @param bmi body mass index of the user
	 * @return 1: overweight; 0: normal weight; -1: underweight
	 */
	public static int getBMIStatus(String sex, int age, double bmi) {
		if(sex.equals("f")) {
			if(age <= 15) {
				if(bmi < 17.7) {
					return -1;
				} else if(bmi >= 17.7 && bmi <= 23.7) {
					return 0;
				} else return 1;
			} else if(age == 16) {
				if(bmi < 18.1) {
					return -1;
				} else if(bmi >= 18.1 && bmi <= 24.2) {
					return 0;
				} else return 1;
			} else if(age == 17) {
				if(bmi < 18.3) {
					return -1;
				} else if(bmi >= 18.3 && bmi <= 24.7) {
					return 0;
				} else return 1;
			} else if(age == 18) {
				if(bmi < 18.5) {
					return -1;
				} else if(bmi >= 18.5 && bmi <= 24.9) {
					return 0;
				} else return 1;
			} else if(age >= 19 && age <= 24) {
				if(bmi < 18.0) {
					return -1;
				} else if(bmi >= 18.0 && bmi <= 23.0) {
					return 0;
				} else return 1;
			} else if(age >= 25 && age <= 34) {
				if(bmi < 19.0) {
					return -1;
				} else if(bmi >= 19.0 && bmi <= 24.0) {
					return 0;
				} else return 1;
			} else if(age >= 35 && age <= 44) {
				if(bmi < 20.0) {
					return -1;
				} else if(bmi >= 20.0 && bmi <= 25.0) {
					return 0;
				} else return 1;
			} else if(age >= 45 && age <= 54) {
				if(bmi < 21.0) {
					return -1;
				} else if(bmi >= 21.0 && bmi <= 26.0) {
					return 0;
				} else return 1;
			} else if(age >= 55 && age <= 64) {
				if(bmi < 22.0) {
					return -1;
				} else if(bmi >= 22.0 && bmi <= 27.0) {
					return 0;
				} else return 1;
			} else if(age >= 65) {
				if(bmi < 24.0) {
					return -1;
				} else if(bmi >= 23.0 && bmi <= 28.0) {
					return 0;
				} else return 1;
			}
		} else {
			if(age <= 15) {
				if(bmi < 17.6) {
					return -1;
				} else if(bmi >= 17.6 && bmi <= 22.8) {
					return 0;
				} else return 1;
			} else if(age == 16) {
				if(bmi < 18.2) {
					return -1;
				} else if(bmi >= 18.2 && bmi <= 23.7) {
					return 0;
				} else return 1;
			} else if(age == 17) {
				if(bmi < 18.7) {
					return -1;
				} else if(bmi >= 18.7 && bmi <= 24.4) {
					return 0;
				} else return 1;
			} else if(age == 18) {
				if(bmi < 19.2) {
					return -1;
				} else if(bmi >= 19.2 && bmi <= 25.0) {
					return 0;
				} else return 1;
			} else if(age >= 19 && age <= 24) {
				if(bmi < 19.0) {
					return -1;
				} else if(bmi >= 19.0 && bmi <= 24.0) {
					return 0;
				} else return 1;
			} else if(age >= 25 && age <= 34) {
				if(bmi < 20.0) {
					return -1;
				} else if(bmi >= 20.0 && bmi <= 25.0) {
					return 0;
				} else return 1;
			} else if(age >= 35 && age <= 44) {
				if(bmi < 21.0) {
					return -1;
				} else if(bmi >= 21.0 && bmi <= 26.0) {
					return 0;
				} else return 1;
			} else if(age >= 45 && age <= 54) {
				if(bmi < 22.0) {
					return -1;
				} else if(bmi >= 22.0 && bmi <= 27.0) {
					return 0;
				} else return 1;
			} else if(age >= 55 && age <= 64) {
				if(bmi < 23.0) {
					return -1;
				} else if(bmi >= 23.0 && bmi <= 28.0) {
					return 0;
				} else return 1;
			} else if(age >= 65) {
				if(bmi < 24.0) {
					return -1;
				} else if(bmi >= 24.0 && bmi <= 29.0) {
					return 0;
				} else return 1;
			}
		}
		return 0;
	}
}