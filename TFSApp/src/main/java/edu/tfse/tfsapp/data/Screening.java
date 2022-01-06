package edu.tfse.tfsapp.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Screening {
	private StringProperty normalHeartRate = new SimpleStringProperty();
	private StringProperty maxHeartRate = new SimpleStringProperty();
	private StringProperty backMobility = new SimpleStringProperty();
	private StringProperty bellyMuscleStrength = new SimpleStringProperty();
	
	public Screening() {
		
	}
	
	public StringProperty getNormalHeartRate() {
		return normalHeartRate;
	}
	public void setNormalHeartRate(String normalHeartRate) {
		this.normalHeartRate.set(normalHeartRate);
	}
	public StringProperty getMaxHeartRate() {
		return maxHeartRate;
	}
	public void setMaxHeartRate(String maxHeartRate) {
		this.maxHeartRate.set(maxHeartRate);
	}
	public StringProperty getBackMobility() {
		return backMobility;
	}
	public int getBackMobilityAsInt() {
		return Integer.parseInt(backMobility.get());
	}
	public void setBackMobility(String backMobility) {
		this.backMobility.set(backMobility);
	}
	public StringProperty getBellyMuscleStrength() {
		return bellyMuscleStrength;
	}
	public int getBellyMuscleStrengthAsInt() {
		return Integer.parseInt(bellyMuscleStrength.get());
	}
	public void setBellyMuscleStrength(String bellyMuscleStrength) {
		this.bellyMuscleStrength.set(bellyMuscleStrength);
	}
}