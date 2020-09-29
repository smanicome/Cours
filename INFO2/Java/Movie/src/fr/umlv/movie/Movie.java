package fr.umlv.movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Movie(String title, List<String> actors) {
	public Movie {
		Objects.requireNonNull(title);
		Objects.requireNonNull(actors);
		
		actors = List.copyOf(actors);
	}
}
