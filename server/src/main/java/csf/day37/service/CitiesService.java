package csf.day37.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csf.day37.models.City;
import csf.day37.repo.CitiesRepo;

@Service
public class CitiesService {
    @Autowired
    private CitiesRepo citiesRepo;

    public Optional<List<City>> getAllCities() {
        List<City> cities = this.citiesRepo.getAllCities();

        if(cities != null)
            return Optional.of(cities);

        return Optional.empty();
    }
}
