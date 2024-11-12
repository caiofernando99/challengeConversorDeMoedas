import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiConection {

    public void ApiConection() {
        try {
            // USD || BRL || EUR
            String valRate = "";
            String valRate2 = "";


            // Definindo a URL da API com sua chave de API
            String url_str = "https://v6.exchangerate-api.com/v6/df7cff62e3e8413ac896bc46/latest/"+ valRate;
            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convertendo a resposta para JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            // Acessando os dados de conversão
            JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");

            double rate2 = conversionRates.get(valRate2).getAsDouble(); // obter a taxa de conversão


            System.out.println("Taxa de conversão de " + valRate + " para " + valRate2 + " :  " + rate2);
          //  System.out.println(readValue + " " + valRate + " em " + valRate2 + " : " + readValue * rate2 );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
