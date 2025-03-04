package csf.day37.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import csf.day37.models.City;
import csf.day37.service.CitiesService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@Controller
public class CitiesController {
    @Autowired
    private CitiesService citiesSvc;

    @GetMapping(path="/api/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCities() {
        Optional<List<City>> optCities = citiesSvc.getAllCities();

        if(optCities.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");

        List<City> cities = optCities.get();

        JsonArrayBuilder jArr = Json.createArrayBuilder();
        for(City city : cities) {
            jArr.add(city.toJson());            
        }

        return ResponseEntity.ok(jArr.build().toString());
    }
}
