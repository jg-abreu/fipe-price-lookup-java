package br.com.jgabreu.fipe.service;

import java.util.List;

public interface IConvertData {
    <T> T getData(String json, Class<T> clazz);

    <T> List<T> getList(String json, Class<T> clazz);
}
