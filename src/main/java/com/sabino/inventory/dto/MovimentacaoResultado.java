package com.sabino.inventory.dto;
import com.sabino.inventory.model.Material;
public record MovimentacaoResultado(String tipo, int quantidade, Material material, boolean abaixoDoMinimo) {}
