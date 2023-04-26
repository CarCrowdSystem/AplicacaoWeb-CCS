package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.models.EnderecoEstacionamento;
import com.google.gson.Gson;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import java.io.IOException;

public class ViaCepService {
    public EnderecoEstacionamento getEndereco(String cep) throws ClientProtocolException, IOException, ParseException {
        EnderecoEstacionamento endereco = null;

        HttpGet request = new HttpGet("https://viacep.com.br/ws/"+cep+"/json/");

        try(CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
            CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);

                Gson gson = new Gson();

                endereco = gson.fromJson(result, EnderecoEstacionamento.class);
            }
        }
        return endereco;
    }
}
