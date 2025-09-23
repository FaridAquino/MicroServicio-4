package com.example.demo.Controller;

import com.example.demo.DTO.TopGlobalEmailDTO;
import com.example.demo.Service.DashboardService;
import com.example.demo.Service.RemoteEmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class TopGlobalEmailController {

    //private TopGlobalEmailService topGlobalEmailService; Se puede llamar direectamente al endpoint apra que haga un sendTopGlobalEmai.
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private RemoteEmailService remoteEmailService;

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/sendemail")
    public ResponseEntity<String> sendEmail(@RequestBody @Valid List<TopGlobalEmailDTO> request) {
        dashboardService.publishData(request);
        String response = remoteEmailService.sendTopGlobalEmail(request);
        //topGlobalEmailService.sendTopGlobalTextEmail(request);
        return ResponseEntity.status(HttpStatus.OK).body("✅ Top daily global emails have been sent successfully and has been published\"");
    }

}
