package com.example.demo.Controller;

import com.example.demo.DTO.TopGlobalEmailDTO;
import com.example.demo.Service.DashboardService;
import com.example.demo.Service.RemoteEmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
@Tag(name = "Administrador", description = "Operaciones exclusivas para administradores")
@SecurityRequirement(name = "bearerAuth")
public class TopGlobalEmailController {

    //private TopGlobalEmailService topGlobalEmailService; Se puede llamar direectamente al endpoint apra que haga un sendTopGlobalEmai.
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private RemoteEmailService remoteEmailService;

    @Operation(
            summary = "Enviar emails globales",
            description = "Envía emails masivos con datos de TikTok y publica la información en el dashboard. Solo disponible para administradores."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Emails enviados y datos publicados exitosamente",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "✅ Emails sent and data published successfully.")
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acceso denegado - Solo administradores pueden enviar emails",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "🚫 Forbidden: Only admins can send emails.")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @PostMapping("/sendemail/{id}")
    public ResponseEntity<String> sendEmail(
            @Parameter(description = "ID del administrador", required = true, example = "123")
            @PathVariable Long id,
            @Parameter(description = "Lista de datos de posts de TikTok para enviar por email", required = true)
            @RequestBody @Valid List<TopGlobalEmailDTO> request,
            @Parameter(description = "Token de autorización Bearer", required = true, example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
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
