package com.project.cleancode.service;

public interface OrderService<T> {

  T filter(Object... args) throws Exception;
}
