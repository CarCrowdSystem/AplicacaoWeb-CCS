package carcrowdsystem.ccs.adapter;

import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.exception.MyException;

public interface DbAdapter<T, N> {

    public T create(N entrada) throws MyException;
    public void update(Integer id, N entrada) throws MyException;
    public Boolean delete(Integer entrada) throws MyException;

}
