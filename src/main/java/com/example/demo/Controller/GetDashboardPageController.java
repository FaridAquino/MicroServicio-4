package com.example.demo.Controller;

import com.example.demo.DTO.TopGlobalEmailDTO;
import com.example.demo.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GetDashboardPageController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/getDashboardInfo")//Si es admin y Usuario
    public ResponseEntity<List<TopGlobalEmailDTO>> sendEmail() {

        List<TopGlobalEmailDTO> dto = dashboardService.getData();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }
}
