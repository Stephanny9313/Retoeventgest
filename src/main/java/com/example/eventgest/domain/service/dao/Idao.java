package com.example.eventgest.domain.service.dao;

import java.util.List;

public interface Idao  <T, ID> {

//IDAO permite que la capa de Servicio no sepa cómo se guardan los datos, sino solo qué acciones puede pedir.
        List<T> getAll();

        void create(T entity);

        void update(T entity);

        void deleteById(ID id);

    }

