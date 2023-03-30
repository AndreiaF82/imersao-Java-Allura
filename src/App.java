import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
   

        // fazer uma conecao HTTP e buscar os top 250 filmes 

        String url=  "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();    
       

        // extrair dados que interessa  titulo o rankin poster 
        var parser = new JsonParse();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);


        // exibir e manipular dados 
   
        var geradora = new GeradoraDeFigurinhas();
    for (Map<String, String> filme : listaDeFilmes) {

        var urlImagem = filme.get("image");
        String titulo = filme.get("title");

        InputStream inputStream = new URL(urlImagem).openStream();
        String nomeArquivo = titulo + " .png";

         
       geradora.cria(inputStream, nomeArquivo);

        System.out.println(titulo);
              System.out.println();
        
    }
            
        }
        
        
    }

