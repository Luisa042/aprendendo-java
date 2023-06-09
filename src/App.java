import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // get data from api
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String json = response.body();

        // parse data
        JsonParser parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(json);

        // show parsed data
        for (Map<String,String> movie : movieList) {
            System.out.println("\u001b[1mTitle:\u001b[0m \u001b[44m " + movie.get("title") + " \u001b[m");
            System.out.println("\u001b[1mImage URL:\u001b[0m " + movie.get("image"));
            System.out.print("\u001b[1mRating:\u001b[0m " + movie.get("imDbRating") + " ");
            
            double classification = Double.parseDouble(movie.get("imDbRating"));
            int stars = (int) classification;
            for (int n = 1; n <= stars; n++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }
    }
}
