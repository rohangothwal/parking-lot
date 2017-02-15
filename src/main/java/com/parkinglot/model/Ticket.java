package com.parkinglot.model;

public class Ticket {
	
	private Car car;
	private Integer parkingSlot;
	
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Integer getParkingSlot() {
		return parkingSlot;
	}
	public void setParkingSlot(Integer parkingSlot) {
		this.parkingSlot = parkingSlot;
	}
	@Override
	public String toString() {
		return parkingSlot + "\t" + car.getRegistrationNumber() + "\t" + car.getColour();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((parkingSlot == null) ? 0 : parkingSlot.hashCode());
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
		Ticket other = (Ticket) obj;
		
		if (parkingSlot == null) {
			if (other.parkingSlot != null)
				return false;
		} else if (!parkingSlot.equals(other.parkingSlot))
			return false;
		return true;
	}
	
}
