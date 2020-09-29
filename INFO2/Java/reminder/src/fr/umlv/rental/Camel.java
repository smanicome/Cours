package fr.umlv.rental;

@SuppressWarnings("preview")
public record Camel(int year) implements Vehicle {
	
	@Override
	public String toString() {
		return "camel " + year;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Camel))
			return false;
		Camel other = (Camel) o;
		return this.year == other.year;
	}
	
	public long insuranceCostAt(int value) {
		return (value - this.year) * 100;
	}
}
