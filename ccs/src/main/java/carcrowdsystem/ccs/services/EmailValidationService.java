/*package carcrowdsystem.ccs.services;
import carcrowdsystem.ccs.response.EmailValidationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailValidationService {
    private static final String ABSTRACT_API_URL = "https://api.abstractapi.com/v1/email-validation";
    private static final String API_KEY = "284e7210ddbe4a86853576b1362089af";

    private final RestTemplate restTemplate;

    public EmailValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EmailValidationResponse validateEmail(String email) {
        String url = ABSTRACT_API_URL + "?api_key=" + API_KEY + "&email=" + email;
        return restTemplate.getForObject(url, EmailValidationResponse.class);
    }
}*/
