## Практическое задание по микросервисной архитектуре 
###Часть I. Netflix OSS

1. Необходимо настроить **config-server** таким образом, чтобы все сервисы, использующие eureka для service-discovery могли доступиться к нему по порту 8282.
Для хранения конфигурации **config-server'а** предагается использовать GIT - заведите отдельный репозиторий.
Менять опцию `server.port: 8989` в **bootstrap.yml** проекта **eureka-server** запрещено.

    Также, **config-server** *не должен* регистрироваться в eureka.
    Запустите **config-server**, он должен подняться на порту `8888`.

2. Настройте и запустите **eureka-server** - он понадобится для service-discovery. 

    См. п. 1 - менять порт в `bootstrap.yml` **нельзя**.

3. Запустите 2 инстанса бизнес-сервиса **workspaces-api**. Данный сервис предоставляет информацию о рабочих местах сотрудников. Для запуска на разных портах можно использовать JVM args (например `-Dserver.port=9090` и `-Dserver.port=9091`) 
Сервис **workspaces-api** должен быть зарегистрирован в eureka, для этого его нужно доработать.

4. Запустите 1 инстанс бизнес-сервиса **employees-api** (`-Dserver.port=9092`), предоставляющего информацию о сотрудниках. Сервис также нужно поправить, чтобы он регистрировался в **eureka**.
 
    Обратите внимание на `EmployeeAPIController`. В строке 29 необходимо получить рабочее место - инстанс `Workspace`. Получить его нужно таким образом, чтобы каждый последующий вызов опрашивал запущенные в п. 3 инстансы **workspaces-api** через round robin. 
    Для вызова сервиса `WorkplaceAPIController` используйте **Feign client**.

5. Если вы сделали всё правильно, вы можете получить информацию о воркспейсах и пользователях GET запросами к ресурсам:
```
http://localhost:9090/workspaces/0000001
http://localhost:9091/workspaces/0000001
http://localhost:9092/employees/0000001
```

при этом вы будете получать 2 разных `serialNumber` при последовательном опросе **employees-api** сервиса. По логам инстансов **workspaces-api** можно заменить, что нагрузка балансируется между инстансами.

Сконфигурируйте и запустите **api-gateway** на порту 9094 и настройте роутинг на бизнес-сервисы. Сделайте так, чтобы информацию по пользователям и воркспейсам можно было получить через ресурсы:
```
http://localhost:9094/workspaces-service/workspaces/0000001
http://localhost:9094/employees-service/employees/0000001
```
