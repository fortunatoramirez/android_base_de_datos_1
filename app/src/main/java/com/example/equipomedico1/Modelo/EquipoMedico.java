package com.example.equipomedico1.Modelo;

public class EquipoMedico {

    private int id;
    private String serie, nombre, descripcion;

    public EquipoMedico(){

    }

    public EquipoMedico(int id, String serie, String nombre, String descripcion) {
        this.id = id;
        this.serie = serie;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getSerie() {
        return serie;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
