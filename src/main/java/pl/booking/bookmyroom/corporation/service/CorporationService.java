package pl.booking.bookmyroom.corporation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.corporation.model.Corporation;
import pl.booking.bookmyroom.corporation.model.CreateCorporationRequest;
import pl.booking.bookmyroom.corporation.model.CorporationRepository;
import pl.booking.bookmyroom.corporation.model.LoginCorporationRequest;

import java.util.List;

@Service
public class CorporationService {

    private final CorporationRepository corporationRepository;

    @Autowired
    public CorporationService(CorporationRepository corporationRepository) {
        this.corporationRepository = corporationRepository;
    }

    public void addCorporation(CreateCorporationRequest request) {
        Corporation corporation = new Corporation();
        corporation.setName(request.getName());
        corporation.setPassword(request.getPassword());
        corporation.setEmail(request.getEmail());
        corporationRepository.save(corporation);
    }

    public String loginCorporation(LoginCorporationRequest request) {
        Corporation corporationToLogin = getAllCorporations()
                .stream()
                .filter(c -> c.getEmail().equals(request.getEmail()))
                .findAny().get();
        if(corporationToLogin.getPassword().equals(request.getPassword())) {
            return "Zalogowano";
        }
        return "Błędny email lub hasło";
    }

    public List<Corporation> getAllCorporations(){
        return corporationRepository.findAll();
    }

}
