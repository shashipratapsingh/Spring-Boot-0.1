package covid19.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class CovidService {

    //private static  final String url = "https://google-translate1.p.rapidapi.com/language/translate/v2/languages";
     //private static final String  XRapidAPIKey = "5ff25fc223msh2ac1cd38fb7f679p1eef15jsna23305e9a896";
     //private static final String XRapidAPIHost = "google-translate1.p.rapidapi.com";
     // cricket
     private static  final String url = "https://cricket-live-data.p.rapidapi.com/series";
     private static final String  XRapidAPIKey = "5ff25fc223msh2ac1cd38fb7f679p1eef15jsna23305e9a896";
     private static final String XRapidAPIHost = "cricket-live-data.p.rapidapi.com";

           @Autowired
          private RestTemplate restTemplate;
              public Object getAllCountryCovidData(){
               try {

                   HttpHeaders headers=new HttpHeaders();
                   headers.set("X-RapidAPI-Key",XRapidAPIKey);
                   headers.set("X-RapidAPI-Host",XRapidAPIHost);
                   //calling api

                   ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(headers),String.class);
                   log.info("output form api: {} ",response.getBody());
                   return response.getBody();

               }catch (Exception e){
                log.error("something went wrong at the time of getting value");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error while endpoint Api");
               }
           }

}
