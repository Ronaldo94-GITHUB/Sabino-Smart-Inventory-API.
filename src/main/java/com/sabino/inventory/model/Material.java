package com.sabino.inventory.model;
public record Material(Long id, String codigo, String descricao, int saldo, int estoqueMinimo) {
    public Material comSaldo(int novoSaldo) { return new Material(id, codigo, descricao, novoSaldo, estoqueMinimo); }
}
