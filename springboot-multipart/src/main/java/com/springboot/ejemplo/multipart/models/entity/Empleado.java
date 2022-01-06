package com.springboot.ejemplo.multipart.models.entity;

import org.springframework.web.multipart.MultipartFile;

public class Empleado {
    private String name;
    private MultipartFile document;

    public Empleado() {
    }

    public Empleado(String name, MultipartFile document) {
        this.name = name;
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getDocument() {
        return document;
    }

    public void setDocument(MultipartFile document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", document=" + document +
                '}';
    }
}

