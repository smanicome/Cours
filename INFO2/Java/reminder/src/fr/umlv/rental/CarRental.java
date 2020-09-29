package fr.umlv.rental;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarRental {
	private final ArrayList<Vehicle> list = new ArrayList<Vehicle>();
	
	public void add(Vehicle vehicle) throws NullPointerException {
		Objects.requireNonNull(vehicle);
		list.add(vehicle);
	}
	
	public void remove(Vehicle vehicle) throws NullPointerException, IllegalStateException {
		Objects.requireNonNull(vehicle);
		if(list.isEmpty())
			throw new IllegalStateException("There is not any vehicle to remove");
		
		if(!list.remove(vehicle)) 
			throw new IllegalStateException("There is not this vehicle in the rental list");
	}
	
	/**
	 * 
	 * @param year
	 * @return null if there is not any vehicle of the given year
	 */
	public List<Vehicle> findAllByYear(int year) {
		List<Vehicle> result = list.stream()
				.filter(vehicle -> vehicle.year() == year)
				.collect(Collectors.toList()); 
		
		return result.isEmpty() ? null : result;
	}
	
	public long insuranceCostAt(int year) throws IllegalArgumentException {
		List<Vehicle> afterYear = list.stream()
				.filter(vehicle -> year >= vehicle.year())
				.collect(Collectors.toList());
		if(list.size() != afterYear.size()) {
			throw new IllegalArgumentException("One or more vehicles were created after");
		}
		
		return list.stream()
				.map((vehicle) -> vehicle.insuranceCostAt(year))
				.reduce((sum, element) -> sum + element)
				.orElse((long) 0);
	}
	
	public Optional<Car> findACarByModel(String model) throws NullPointerException {
		Objects.requireNonNull(model);
		
		for(var vehicle : list) {
			if(checkModel(vehicle, model))
				return Optional.of((Car) vehicle);
		}
		
		return Optional.empty();
	}
	
	private final boolean checkModel(Vehicle vehicle, String model) throws NullPointerException {
		Objects.requireNonNull(vehicle);
		Objects.requireNonNull(model);
		
		if(!(vehicle instanceof Car))
			return false;
		
		Car car = (Car) vehicle;
		
		return car.model().equals(model);
	}

	@Override
	public String toString() {
		return list.stream()
				.map(Vehicle::toString)
				.collect(Collectors.joining("\n"));
		
//		StringBuilder builder = new StringBuilder();
//		
//		if(list.isEmpty())
//			return "";
//		
//		list.forEach(car -> builder.append(car.toString()).append('\n'));
//		builder.setLength(builder.length() - 1);
//		
//		return builder.toString();
	}
}
