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

    @PostMapping("/sendemail/{id}")
    public ResponseEntity<String> sendEmail(
            @PathVariable Long id,
            @RequestBody List<TopGlobalEmailDTO> request,
            @RequestHeader("Authorization") String authHeader) {

        // Extraer el token sin el "Bearer "
        String jwtToken = authHeader.replace("Bearer ", "");

        // Obtener el rol desde el microservicio de cuentas
        String role = remoteEmailService.getUserRole(id, jwtToken);

        if (role == null || !role.equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("🚫 Forbidden: Only admins can send emails.");
        }

        dashboardService.publishData(request);

        return ResponseEntity.ok("✅ Emails sent and data published successfully.");
    }


}
