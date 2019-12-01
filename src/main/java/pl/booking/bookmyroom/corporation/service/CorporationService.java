package pl.booking.bookmyroom.corporation.service;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
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

    public boolean addCorporation(CreateCorporationRequest request) {
        if(!getCorporationByEmail(request.getEmail()).isEmpty()) {
            return false;
        } else {
            Corporation corporation = new Corporation();
            corporation.setName(request.getName());
            if(request.getPassword().equals(request.getRepeatedPassword())) {
                corporation.setPassword(request.getPassword());
            } else {
                return false;
            }
            corporation.setEmail(request.getEmail());
            PasswordGenerator gen = new PasswordGenerator();
            corporation.setAdminPassword(gen.generatePassword(14, new CharacterRule(EnglishCharacterData.Alphabetical)));
            System.out.println(corporation.getAdminPassword());
            corporationRepository.save(corporation);
            return true;
        }
    }

    public boolean loginCorporation(LoginCorporationRequest request) {
        return getCorporationByEmail(request.getEmail()).stream()
                .anyMatch(c -> c.getPassword().equals(request.getPassword()));
    }

    public List<Corporation> getAllCorporations(){
        return corporationRepository.findAll();
    }

    public List<Corporation> getCorporationByEmail(String email){
        return corporationRepository.findCorporationByEmail(email);
    }

    public boolean loginAdminCorp(LoginCorporationRequest request) {
        return getCorporationByEmail(request.getEmail()).stream()
                .anyMatch(c -> c.getAdminPassword().equals(request.getPassword()));
    }
}
