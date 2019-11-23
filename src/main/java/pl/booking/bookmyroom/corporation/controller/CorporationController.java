package pl.booking.bookmyroom.corporation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.booking.bookmyroom.corporation.model.Corporation;
import pl.booking.bookmyroom.corporation.model.CreateCorporationRequest;
import pl.booking.bookmyroom.corporation.model.LoginCorporationRequest;
import pl.booking.bookmyroom.corporation.service.CorporationService;

import java.util.List;

@RestController
@RequestMapping("/corporations")
public class CorporationController {

    private final CorporationService corporationService;

    @Autowired
    public CorporationController(CorporationService corporationService) {
        this.corporationService = corporationService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addCorporation(@RequestBody CreateCorporationRequest request) {
        corporationService.addCorporation(request);
    }

    @GetMapping("login")
    public String loginCorporation(@RequestBody LoginCorporationRequest request){
        return corporationService.loginCorporation(request);
    }

    @GetMapping
    public List<Corporation> getAllCorporations(){
        return corporationService.getAllCorporations();
    }

}
