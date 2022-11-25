[Описание задания](https://github.com/shade1471/qa-diplom/blob/master/Project%20Description.md)

**Артефакты проекта:**
 - [Тест кейсы](/docs/Cases.xlsx)
 - [Чек лист](/docs/Check.xlsx)
 - [План тестирования](/docs/docs/Plan.md)
 - [Отчет по тестированию](/docs/docs/Report.md)
 - [Сводный отчет о работе](/docs/docs/Symmary.md)

*Требование к устройству на котором будет произведен запуск автотестов*: Русская локаль устройства, на ПК установлен Android SDK

# Порядок действий для запуска автотестов:

1. Сделать клон репозитория, воспользовавшись командой: ``git clone https://github.com/shade1471/qa-diplom.git``

2. Запустить автотесты можно как через cреду разработки Android Studio, так и воспользовавшись CLI в каталоге проекта

2.1 Вариант запуска через Android Studio и формирования отчетов с помощью фреймворка Allure.

Открыть склонированный репозиторий как проект в Android Studio.

  В проекте реализовано 4 тестовых класса на Java, содержащие автотесты на соответствующий функционал:
  - ClaimFunctionalTest - Проверка функционала Заявок
  - LoginTest - Проверка функционала авторизации в приложении
  - NewsFunctionalTest - Проверка функционала новостей
  - PagesTest - Проверка GUI
  
  Открыв нужный тестовый класс, можно запустить его на исполнение
![run](https://user-images.githubusercontent.com/90593727/204017357-d9ace4f1-2ca6-4c73-87b4-9257ba464e9f.png)

После запуска всех тестовых классов, нужно забрать с файловой системы устройства на котором было произведено тестирование, сформированный Allure отчет:

Либо через Device File Explorer по пути: ``/data/data/ru.iteco.fmhandroid/files/allure-results``

![saveas](https://user-images.githubusercontent.com/90593727/204020068-af4d81b2-b45d-470c-a819-5753e4f42896.png)

Либо воспользоваться коммандой ``adb pull /data/data/ru.iteco.fmhandroid/files/allure-results``

В проекте Allure отчет о тестировании доступен в каталоге [allure-results](/allure-results)

В каталоге allure-results, при условии установленного на ПК Allure, выполнить комманду ``allure serve ./``

2.2 Вариант запуска через CLI с помощью gradlew

В каталоге проекта через терминал выполнить ``./gradlew connectedAndroidTest``

После прогона атотестов, отчет сформированный gradle будет доступен по пути - ``/[projectFolder]/app/build/reports/androidTests/connected/index.html``

