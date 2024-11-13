package br.com.alura.challenges.currency.converter.services.impls;

import br.com.alura.challenges.currency.converter.services.IExchangeService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Service implementation for handling exchange rate requests.
 */
public class ExchangeService implements IExchangeService {

    /**
     * Sends a request to the specified URI and returns the response.
     *
     * @param uri the URI to send the request to.
     * @return the HttpResponse containing the response body as a String.
     */
    @Override
    public HttpResponse<String> request(String uri) {
        try {
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("The request cannot be established or the operation has been interrupted.");
        }
        return null;
    }
}