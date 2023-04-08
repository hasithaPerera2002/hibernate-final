package service;

public interface SuperService <T,String> {
    int add(T obj);
    int delete(T obj);
    int update(T obj);

}

