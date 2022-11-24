package Repositorios;

public interface Specification<T> {
  boolean exists(T t);
}