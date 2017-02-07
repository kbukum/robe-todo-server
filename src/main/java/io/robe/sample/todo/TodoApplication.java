package io.robe.sample.todo;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.robe.admin.RobeApplication;
import io.robe.hibernate.RobeHibernateBundle;
import io.robe.sample.todo.cli.TodoInitialCommand;

/**
 *
 */
public class TodoApplication extends RobeApplication<TodoConfiguration> {

    @javax.inject.Inject
    protected RobeHibernateBundle hibernateBundle;

    public static void main(String[] args) throws Exception {
        TodoApplication application = new TodoApplication();
        application.run(args);
    }

    @Override
    public void initialize(Bootstrap<TodoConfiguration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.addCommand(new TodoInitialCommand(this, "init", "Runs Hibernate and initialize required columns"));
    }

    @Override
    public void run(TodoConfiguration configuration, Environment environment) throws Exception {
        super.run(configuration, environment);
    }
}

