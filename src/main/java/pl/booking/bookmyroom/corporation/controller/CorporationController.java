package pl.booking.bookmyroom.corporation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.booking.bookmyroom.corporation.model.Corporation;
import pl.booking.bookmyroom.corporation.model.CreateCorporationRequest;
import pl.booking.bookmyroom.corporation.model.LoginCorporationRequest;
import pl.booking.bookmyroom.corporation.service.CorporationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/corporations")
@CrossOrigin(value = "http://localhost:8000")
public class CorporationController {

    private final CorporationService corporationService;

    @Autowired
    public CorporationController(CorporationService corporationService) {
        this.corporationService = corporationService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public boolean addCorporation(@RequestBody @Valid CreateCorporationRequest request) {
        return corporationService.addCorporation(request);
    }

    @GetMapping("login")
    public boolean loginCorporation(@RequestBody @Valid LoginCorporationRequest request){
        return corporationService.loginCorporation(request);
    }

    @GetMapping
    public List<Corporation> getAllCorporations(){
        return corporationService.getAllCorporations();
    }
}
