package com.ftn.uns.ac.rs.theperfectmeal.helper;

public interface MapperInterface<T,U> {

    T toEntity(U dto);

    U toDto(T entity);
}
