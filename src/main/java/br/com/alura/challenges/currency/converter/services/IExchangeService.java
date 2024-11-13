package br.com.alura.challenges.currency.converter.services;

import java.net.http.HttpResponse;

/**
 * Interface for handling exchange rate requests.
 */
public interface IExchangeService {

    /**
     * Sends a request to the specified URI and returns the response.
     *
     * @param uri the URI to send the request to.
     * @return the HttpResponse containing the response body as a String.
     */
    HttpResponse<String> request(String uri);
}