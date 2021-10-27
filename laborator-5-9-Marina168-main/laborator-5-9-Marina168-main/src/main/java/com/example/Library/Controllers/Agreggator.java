package com.example.Library.Controllers;

import com.example.Library.Entities.Author;
import com.example.Library.Entities.Book;
import com.example.Library.Services.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="/api/bookCollection")
public class Agreggator {


    private RestTemplate restTemplate = new RestTemplate();

    private final AuthorService authorService;

    public Agreggator(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping()
    public ResponseEntity<?> bookDetails(@RequestBody Book book) throws IOException, InterruptedException {

        HttpClient httpclient = HttpClient.newHttpClient();

        var values = new HashMap<String, String>() {{
            put("title", book.getTitle());
            put("publisher", book.getPublisher());
            put("published", book.getPublished().toString());
            put("summary", book.getSummary());
            put("genre", book.getGenre());
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);
        HttpRequest httppost = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/library/books"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody)).setHeader(HttpHeaders.CONTENT_TYPE,"application/json").build();

        HttpResponse<String> response = httpclient.send(httppost,
                HttpResponse.BodyHandlers.ofString());

        String bookId = response.body();
        System.out.println(response);
        int noAuthors =book.getAuthors().size();
        List<Integer> authorId = new ArrayList<>();
        for(int i=0;i<noAuthors;i++)
        {

            Author a = book.getAuthors().get(i);
            if(authorService.authorExist(a.getName(),a.getReview())!=-1)
            {
                authorId.add(authorService.authorExist(a.getName(),a.getReview()));
            }
            else
            {
                var values1 = new HashMap<String, String>() {{
                    put("name",a.getName());
                    put("review",a.getReview().toString());
                }};

                requestBody = objectMapper
                        .writeValueAsString(values1);
                httppost = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/api/library/authors/author"))
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody)).setHeader(HttpHeaders.CONTENT_TYPE,"application/json").build();
                response = httpclient.send(httppost,
                        HttpResponse.BodyHandlers.ofString());

                authorId.add(Integer.parseInt(response.body()));

            }
        }

        for(int i=0;i<noAuthors;i++)
        {
             httppost = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/library/book/" +bookId+ "/author/" + authorId.get(i)))
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody)).setHeader(HttpHeaders.CONTENT_TYPE,"application/json").build();
             response = httpclient.send(httppost,
                    HttpResponse.BodyHandlers.ofString());
        }
        System.out.println(response);
        return new ResponseEntity<>("Agregator " ,HttpStatus.OK);

    }
}

