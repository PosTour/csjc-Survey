Димидов Илья, @pos_tu

К проекту прилагается папка backup, в ней расположен файл Survey.backup.

Для поднятия базы данных необходимо создать новую базу данных "Survey" в PostgreSQL
и наполнить ее с помощью Survey.backup.

Затем нужно внести данные пользователя postgreSQL в файл config.properties, расположенный по адресу
"Common Utils\src\main\resources\config.properties".


Проект представляет собой сервис для прохождения социального опроса. он предоставляет возможность пользователям выбрать волнующие их проблемы в обществе 
и указать пути решения, которые они считают подходящими, и возможность получения результатов опроса в виде статистики и в виде списка комментариев пользователей.

Проект имеет две точки входа: через "Result Viewing\src\main\java\ResultViewer.java"
и через "Survey\User Survey\src\main\java\Survey.java" для просмотра результатов опроса и  прохождения
опроса соответственно.