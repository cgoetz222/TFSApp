package edu.tfse.tfsapp.data;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import edu.tfse.tfsapp.helper.BMI;
import edu.tfse.tfsapp.helper.MathHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	public static int NORMAL_WEIGHT = 0;
	public static int LOW_WEIGHT = -1;
	public static int HIGH_WEIGHT = 1;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	private StringProperty name = new SimpleStringProperty();
	private StringProperty sex = new SimpleStringProperty();
	private StringProperty birthdate = new SimpleStringProperty();
	private StringProperty age = new SimpleStringProperty();
	private StringProperty height = new SimpleStringProperty();
	private StringProperty weight = new SimpleStringProperty();
	private StringProperty weightClassification = new SimpleStringProperty();
	private StringProperty bmi = new SimpleStringProperty();
	private StringProperty bmiRange = new SimpleStringProperty();
	private int bmiStatus = 0;
		
	public User() {		
	}
	
	public User(String name, String sex, String age, String height, String weight) {	
		this.name.set(name);
		this.age.set(age);
		this.height.set(height);
		this.weight.set(weight);
		this.sex.set(sex);
		
		update();
	}
	public StringProperty getName() {
		return name;
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty getAge() {
		return age;
	}
	public int getAgeAsInt() {
		return Integer.parseInt(age.get());
	}
	public void setAge(String age) {
		this.age.set(age);
	}
	public StringProperty getBirthdate() {
		return birthdate;
	}
	public LocalDate getBirthdateAsDate() {
     	//convert String to LocalDate
		return LocalDate.parse(birthdate.get(), formatter);
	}
	public void setBirthdate(String birthdate) {
		this.birthdate.set(birthdate);
		
		calcAndSetAge();
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate.set(birthdate.format(formatter));
	}
	public StringProperty getBMI() {
		return bmi;
	}
	public StringProperty getBMIRange() {
		return bmiRange;
	}
	public void setBMIRange(String bmiRange) {
		this.bmiRange.set(bmiRange);
	}
	public StringProperty getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height.set(height);
	}
	public StringProperty getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight.set(weight);
	}
	public StringProperty getWeightClassification() {
		return weightClassification;
	}
	public void setWeightClassification(String weightClassification) {
		this.weightClassification.set(weightClassification);
	}
	public StringProperty getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex.set(sex);
	}
	public int getBMIStatus() {
		return bmiStatus;
	}
	
	private void calcAndSetAge() {
		LocalDate currentDate = LocalDate.now();
		setAge(Integer.valueOf(Period.between(getBirthdateAsDate(), currentDate).getYears()).toString());
	}
	
	public void update() {
		double dWeight;
		double dHeight;
		double dBMI;
    	String bundleName = "edu.tfse.tfsapp.views.views"; //$NON-NLS-1$
    	ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName);

		dWeight = Double.parseDouble(weight.get()); 
		dHeight = Double.parseDouble(height.get());
		dBMI = dWeight / (dHeight/100.0 * dHeight/100.0);
		dBMI = MathHelper.round(dBMI, 1);
		bmi.set(String.format("%.1f", dBMI));
		
		calcAndSetAge();
		
		this.setBMIRange(BMI.getBMIRange(sex.get(), getAgeAsInt()));
		
		bmiStatus = BMI.getBMIStatus(sex.get(), getAgeAsInt(), dBMI);
		if(bmiStatus == -1) {
			this.setWeightClassification(resourceBundle.getString("main.weightClassLow"));
		} else if(bmiStatus == 0) {
			this.setWeightClassification(resourceBundle.getString("main.weightClassNormal"));
		} else {
			this.setWeightClassification(resourceBundle.getString("main.weightClassHigh"));
		}
	}
}