**Отчет о проведенном тестировании**

Было запланировано автоматизировать 25 тестовых сценариев. Все сценарии удалось реализовать.

Отсутствие ТЗ к функционалу приложения накладывает отпечаток, что разработка автотестов и ожидаемое поведение при проверках, основывается на личном опыте инженера по тестированию.

Отсутствие связи с командой разработки не позволило более качественно создать автотесты в плане работы с ожиданиями в асинхронных функциях.

Время требующиеся на прохождение автотестов порядка 12-15 минут, с учетом не самой лучшей реализации через ожидания функций sleep.

Время требующиеся на ручной прогон тестов по чек листу, порядка 25-30 минут с учетом не сильно объемного набора текста в тех полях, где это требуется.

Если рассматривать полученный результат в долгосрочной перспективе, в работе по проекту. Автотесты будут сильно экономить время при регрессионом тестировании, при добавлении нового функционала. Также если встроить их в концепцию CI/CD , прогон тестов по основному фунционалу для каждой сборки, будет высвобождать время на проверку внедрения нового функционала.

**Запланировано время:** 48 часов

**Фактически потрачено времени:** ориентировочно 60 часов

Разница между запланированным и потраченным временем обусловлена незнакомым функционалом фреймворка Espresso и дополнительно потраченном времени на изучение инструмента.
Так же дополнительным временем для формирования отчетной документации, заведением Issues на найденные дефекты.