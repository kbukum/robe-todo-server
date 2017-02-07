package io.robe.sample.todo.dao;

import io.robe.hibernate.dao.BaseDao;
import io.robe.sample.todo.entity.TodoItem;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

/**
 * Created by kamilbukum on 06/02/2017.
 */
public class TodoItemDao extends BaseDao<TodoItem> {

    @Inject
    public TodoItemDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
