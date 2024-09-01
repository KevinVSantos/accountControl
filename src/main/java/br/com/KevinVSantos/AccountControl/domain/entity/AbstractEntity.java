package br.com.KevinVSantos.AccountControl.domain.entity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractEntity<T> implements Serializable {

    public abstract T getGenericId();

}
