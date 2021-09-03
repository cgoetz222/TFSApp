package edu.tfse.tfsapp.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private StringProperty name = new SimpleStringProperty();
	private StringProperty age = new SimpleStringProperty();;
	private StringProperty height = new SimpleStringProperty();;
	private StringProperty weight = new SimpleStringProperty();;
	
	public User() {
		
	}
	public User(String name, String age, String height, String weight) {
		this.name.set(name);
		this.age.set(age);
		this.height.set(height);
		this.weight.set(weight);
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
	public void setAge(String age) {
		this.age.set(age);
	}
	public StringProperty getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height.set(height);;
	}
	public StringProperty getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight.set(weight);;
	}
}