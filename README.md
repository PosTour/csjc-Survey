Димидов Илья, @pos_tu

К проекту прилагается папка backup, в ней расположен файл Survey.backup.

Для поднятия базы данных необходимо создать новую базу данных "Survey" в PostgreSQL
и наполнить ее с помощью Survey.backup.

Затем нужно внести данные пользователя (владельца базы данных) в файл config.properties, расположенный по адресу
"Common Utils\src\main\resources\config.properties".

Программа имеет две точки входа через "Result Viewing\src\main\java\ResultViewer.java"
и "Survey\User Survey\src\main\java\Survey.java" для просмотра результатов опроса и  прохождения
опроса соответственно.