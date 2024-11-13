package br.com.alura.challenges.currency.converter.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class IExchangeServiceTest {

    @Mock
    private IExchangeService exchangeService;

    @Mock
    private HttpResponse<String> httpResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRequest() {
        String uri = "http://example.com";
        String expectedResponse = "response body";

        when(exchangeService.request(uri)).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(expectedResponse);

        HttpResponse<String> response = exchangeService.request(uri);
        assertNotNull(response);
        assertEquals(expectedResponse, response.body());

        verify(exchangeService).request(uri);
    }
}