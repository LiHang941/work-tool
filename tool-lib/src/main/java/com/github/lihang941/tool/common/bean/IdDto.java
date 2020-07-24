package com.github.lihang941.tool.common.bean;

/**
 * @author : lihang1329@gmail.com
 */
public class IdDto<T> {

    private T id;

    public IdDto(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public IdDto<T> setId(T id) {
        this.id = id;
        return this;
    }
}
