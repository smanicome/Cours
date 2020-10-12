package fr.umlv.healthcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class HealthCheck {
    @FunctionalInterface
    public interface URIFinder {
        Optional<URI> find();

        static URIFinder fromArguments(String[] uris) {
            Objects.requireNonNull(uris);

            return () -> {
                if (uris.length == 0 || uris[0] == null)
                    return Optional.empty();

                return URIfy(uris[0]);
            };
        }

        static URIFinder fromURI(String uri) {
            Objects.requireNonNull(uri);
            return () -> URIfy(uri);
        }

        private static Optional<URI> URIfy(String uri) {
            try {
                return Optional.of(URI.create(uri));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        }

        default URIFinder or(URIFinder other) {
            Objects.requireNonNull(other);

            return () -> {
                Optional<URI> optionalURI = this.find();
                if (optionalURI.isEmpty()) {
                    optionalURI = other.find();
                }
                return optionalURI;
            };
        }

        static URIFinder fromMapGetLike(String key, UnaryOperator<String> getUri) {
            Objects.requireNonNull(key);
            Objects.requireNonNull(getUri);

            return () -> {
                String uri = getUri.apply(key);

                if (uri == null)
                    return Optional.empty();

                return URIfy(uri);
            };
        }

        static URIFinder fromPropertyFile(String key, Path path) {
            Objects.requireNonNull(key);
            Objects.requireNonNull(path);

            return () -> {
                Properties properties = new Properties();
                try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
                    properties.load(bufferedReader);

                    String property = (String) properties.get(key);
                    if (property == null)
                        return Optional.empty();

                    try {
                        return Optional.of(URI.create(property));
                    } catch (IllegalArgumentException e) {
                        return Optional.empty();
                    }
                } catch (IOException e) {
                    return Optional.empty();
                }
            };
        }
    }

    static boolean healthCheck(URI uri) throws InterruptedException {
        Objects.requireNonNull(uri);

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(uri).build();
        try {
            HttpResponse<Void> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.discarding());
            return httpResponse.statusCode() == 200;
        } catch (IOException e) {
            return false;
        }
    }
}
