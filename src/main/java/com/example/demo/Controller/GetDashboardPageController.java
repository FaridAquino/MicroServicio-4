package com.example.demo.Controller;

import com.example.demo.DTO.TopGlobalEmailDTO;
import com.example.demo.Service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Dashboard", description = "Operaciones relacionadas con el dashboard")
public class GetDashboardPageController {
    @Autowired
    private DashboardService dashboardService;

    @Operation(
            summary = "Obtener información del dashboard",
            description = "Retorna una lista de datos del dashboard con información de posts de TikTok"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Datos del dashboard obtenidos exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TopGlobalEmailDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping("/getDashboardInfo")//Si es admin y Usuario
    public ResponseEntity<List<TopGlobalEmailDTO>> sendEmail() {

        List<TopGlobalEmailDTO> dto = dashboardService.getData();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(
            summary = "Health Check",
            description = "Verifica el estado del microservicio"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Servicio funcionando correctamente",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "OK")
                    )
            )
    })
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }
}
