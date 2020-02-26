package com.codecool.shop.controller.requestprocessing.ajax;

public interface JsonProvider<T> {
    String stringify(T object);
}
