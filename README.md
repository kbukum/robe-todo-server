## Robe Todo Sample Server

#### A Sample Project 


#### Motivation

It is a sample to create project with Robe-Server. 
You can find more information about robe server on the [https://github.com/robeio/robe](https://github.com/robeio/robe) link.

#### What's inside

It's extended from robe-admin module which is module in robe framework. It gets all integrations with robe modules from the robe-admin.
That's why it supports : 

* Authentication [Robe-Auth](https://github.com/robeio/robe/blob/master/docs/en/modules/robe-auth.md)
* Hibernate [Robe-Hibernate](https://github.com/robeio/robe/blob/master/docs/en/modules/robe-hibernate.md)
* Quartz [Quartz API](https://github.com/robeio/robe/blob/master/docs/en/modules/robe-quartz.md)	
* Mail [Robe-Mail](https://github.com/robeio/robe/blob/master/docs/en/modules/robe-mail.md)
* Assets [Robe-Assets](https://github.com/robeio/robe/blob/master/docs/en/modules/robe-assets.md)
* Transformations [Robe-Convert](https://github.com/robeio/robe/blob/master/docs/en/modules/robe-convert.md)
* Commons [Robe-Common](https://github.com/robeio/robe/blob/master/docs/en/modules/robe-common.md)	

#### Development Mode

##### with http resource <a name="start_dev"></a>

* main class : org.example.todo.TodoApplication
* Program arguments : server todo_dev.yml
* index : server will start on 8282 port.

##### with filesystem resource <a name="start_prod"></a>

* main class : org.example.todo.TodoApplication
* Program arguments : server todo.yml
* index : server will start on 8181 port.