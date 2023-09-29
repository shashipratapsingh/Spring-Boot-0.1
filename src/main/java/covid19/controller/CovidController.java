package covid19.controller;

import covid19.service.CovidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid")
@RequiredArgsConstructor
public class CovidController {

    private  final CovidService covidService;
    @GetMapping("/co")
     public ResponseEntity<?> callRapinEndPint(){
         return ResponseEntity.ok(covidService.getAllCountryCovidData());
     }

}
