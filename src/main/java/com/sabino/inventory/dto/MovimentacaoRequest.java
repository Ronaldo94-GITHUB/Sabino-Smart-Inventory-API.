package com.sabino.inventory.dto;
import jakarta.validation.constraints.*;
public record MovimentacaoRequest(@NotNull Long materialId, @NotBlank String tipo, @Min(1) int quantidade) {}
