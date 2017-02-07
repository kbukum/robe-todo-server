package io.robe.sample.todo.entity;

import io.robe.hibernate.entity.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by kamilbukum on 06/02/2017.
 */
@Entity
public class TodoItem extends BaseEntity {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
