package com.parkinglot.model;

public class Car {
	private String registrationNumber;
	private String colour;
	
	public Car(String registrationNumber, String colour) {
		super();
		this.registrationNumber = registrationNumber.trim();
		this.colour = colour.trim();
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber.trim();
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour.trim();
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Car [registrationNumber=" + registrationNumber + ", colour=" + colour + "]";
	}
	
}
