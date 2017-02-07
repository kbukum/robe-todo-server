package io.robe.sample.todo.cli;

import io.dropwizard.Application;
import io.robe.admin.cli.InitializeCommand;
import io.robe.guice.GuiceBundle;
import io.robe.hibernate.RobeHibernateBundle;
import io.robe.sample.todo.TodoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kamilbukum on 06/02/2017.
 */
public class TodoInitialCommand extends InitializeCommand<TodoConfiguration>{

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoInitialCommand.class);

    @javax.inject.Inject
    protected RobeHibernateBundle hibernateBundle;

    public TodoInitialCommand(Application<TodoConfiguration> service, String name, String description) {
        super(service, name, description);
        GuiceBundle.getInjector().injectMembers(this);
        setHibernateBundle(hibernateBundle);
    }

}
