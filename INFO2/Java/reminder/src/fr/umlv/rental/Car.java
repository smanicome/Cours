package fr.umlv.rental;

import java.util.Objects;

@SuppressWarnings("preview")
public record Car(String model, int year) implements Vehicle {
	public Car {
		Objects.requireNonNull(model);
	}

	@Override
	public String toString() {
		return model + " " + year;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Car))
			return false;
		Car other = (Car) o;
		return this.model.equals(other.model) && this.year == other.year;
	}
	
	public long insuranceCostAt(int value) {
		if( (value - this.year) < 10) return 200;
		return 500;
	}
}
