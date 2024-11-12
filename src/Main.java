import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //menu
        Menu menu = new Menu();
        menu.ShowMenu();
        Scanner scanner = new Scanner(System.in);
        int readOption = Integer.parseInt(scanner.nextLine());

    while (readOption !=7){
        if (readOption < 0 || readOption > 7) {
            System.out.println("Erro: opção inválida");
            break;
        } else {
            System.out.println("Insira o valor a ser convertido: ");
            System.out.println("insira 0 para encerrar.");
        }
        double readValue = scanner.nextDouble();
        if (readValue == 0){
            break;
        }

        // Seleciona valores  || USD || BRL || EUR
        String valRate = "";
        String valRate2 = "";

        switch (readOption) {
            case 1:
                valRate = "USD";
                valRate2 = "BRL";
                break;
            case 2:
                valRate = "BRL";
                valRate2 = "USD";
                break;
            case 3:
                valRate = "EUR";
                valRate2 = "BRL";
                break;
            case 4:
                valRate = "BRL";
                valRate2 = "EUR";
                break;
            case 5:
                valRate = "EUR";
                valRate2 = "USD";
                break;
            case 6:
                valRate = "USD";
                valRate2 = "EUR";
                break;
            case 7:
                break;
        }

        // ApiConection
        try {
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
            System.out.println(readValue + " " + valRate + " em " + valRate2 + " : " + readValue * rate2 );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
}

