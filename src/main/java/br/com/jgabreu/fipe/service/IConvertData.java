package br.com.jgabreu.fipe.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> classe);
}
