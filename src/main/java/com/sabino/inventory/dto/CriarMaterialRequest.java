package com.sabino.inventory.dto;
import jakarta.validation.constraints.*;
public record CriarMaterialRequest(@NotBlank String codigo, @NotBlank String descricao,
    @Min(0) int saldoInicial, @Min(0) int estoqueMinimo) {}
