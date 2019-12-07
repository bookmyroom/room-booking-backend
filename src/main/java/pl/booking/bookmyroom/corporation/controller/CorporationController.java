package pl.booking.bookmyroom.corporation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.booking.bookmyroom.corporation.model.Corporation;
import pl.booking.bookmyroom.corporation.model.CreateCorporationRequest;
import pl.booking.bookmyroom.corporation.model.LoginCorporationRequest;
import pl.booking.bookmyroom.corporation.service.CorporationService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/corporations")
@CrossOrigin
public class CorporationController {

    private final CorporationService corporationService;

    @Autowired
    public CorporationController(CorporationService corporationService) {
        this.corporationService = corporationService;
    }

    @PostMapping(value = "/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> addCorporation(@RequestBody @Valid CreateCorporationRequest request) {
        if(!corporationService.addCorporation(request))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<String> loginCorporation(HttpServletRequest sReq, @RequestBody @Valid LoginCorporationRequest request){
        if(!corporationService.loginCorporation(sReq, request))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Corporation> getAllCorporations(){
        return corporationService.getAllCorporations();
    }
}
