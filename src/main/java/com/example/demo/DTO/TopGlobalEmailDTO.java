package com.example.demo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "DTO para datos de posts de TikTok que se envían por email globalmente")
public class TopGlobalEmailDTO {
    @Schema(description = "ID del administrador que envía el email", example = "123", required = true)
    @NotNull
    private Long adminId;
    
    @Schema(description = "Hashtag utilizado en el post de TikTok", example = "#viral #trending", required = true)
    @NotBlank
    private String usedHashTag;
    
    @Schema(description = "ID único del post de TikTok", example = "7123456789012345678", required = true)
    @NotNull
    private String postId;
    
    @Schema(description = "Fecha en que se publicó el post", example = "2024-01-15", required = true)
    @NotNull
    private LocalDate datePosted;
    
    @Schema(description = "Nombre de usuario de la cuenta de TikTok", example = "@username", required = true)
    @NotBlank
    private String usernameTiktokAccount;
    
    @Schema(description = "URL completa del post de TikTok", example = "https://www.tiktok.com/@username/video/7123456789012345678", required = true)
    @NotBlank
    private String postURL;
    
    @Schema(description = "Número de visualizaciones del post (mínimo 1)", example = "15000", minimum = "1", required = true)
    @NotNull
    @Min(value = 1, message = "At least 1 view must be requested")
    private Integer views;
    
    @Schema(description = "Número de likes del post (mínimo 1)", example = "850", minimum = "1", required = true)
    @NotNull
    @Min(value = 1, message = "At least 1 like must be requested")
    private Integer likes;
    
    @Schema(description = "Tasa de engagement del post (likes/views)", example = "0.056", required = true)
    @NotNull
    private Double engagement;
}
