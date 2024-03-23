# Pastebin-project
inspired by - https://pastebin.com/

_Pastebin_ - приложение в котором пользователи могут создавать обычные _блоки текста_, сохранять их в системе, и делиться этими _ссылками (текстом)_ с другими пользователями по Сети.

![Context Diagram (general_v1 0 0)](https://github.com/alibekbirlikbai/Pastebin-project/assets/87764579/61248e86-bc42-494f-aaba-9780c68a74fe)


---


## Features
<details>
  <summary><i>*v1.0.0 (краткое описание: Pastebin-project MVP)</i></summary>

- [x] 1. ~~Пользователь может создать блок Текста, и загрузить его в систему;~~
- [x] 2. ~~Система должна генерировать уникальный URL адрес (ссылку) на этот блок текста;~~
- [x] 3. ~~Пользователь может отправить эту ссылку другому пользователю, который перейдя по ней, увидит тот же блок текста;~~

</details>


<details>
  <summary><i>v1.1.0 (краткое описание: доп. функционал)</i></summary>

- [x] 4. ~~Блоки текста и ссылки деактивируются через какое-то время и удаляются из системы;~~
    - [x] [**--EVALUATE--. _*comment: Этот функционал лучше реализовать на стороне клиента_**] ~~(4.1. Пользователь может сам указать когда именно это происходит;)~~
- [x] 5. ~~Можно определять Категорию вводимого блока текста;~~
    - [x] ~~5.1. Поиск по Категории;~~
- [x] ~~6. Можно определять Пароли на ссылки;~~
- [x] 7. ~~**[Tech-feature]** Сокрытие всех дополнительных параметров на URL ссылке, пользователь не должен их видеть (все дополнительные параметры должны храниться в БД, и обрабатываться на стороне сервера);~~

</details>


<details open>
  <summary><i>[Upcoming] v1.2.0 (краткое описание: Cloud implementation)</i></summary>

- [ ] 8. **[Tech-feature]** Реализация сохранения контента в настоящее Облако (замена Cloud-Simulation);
- [ ] 9. **[Tech-feature]** Реализация простого Front-end для проекта (Angular/ts);
- [ ] 10. **[Tech-feature]** Deployment проекта в Интернете;

</details>


---


## Project versions
> [!NOTE]
> Рекомендую сначало ознакомится с [_*v1.0.0 (Pastebin-project MVP)_](https://github.com/alibekbirlikbai/Pastebin-project/releases/tag/v1.0.0)
> <br> В этом релизе был описан:
> - основной _workflow_ проекта;
> - принципы работы _API (+user experience)_;
>   - _Logs_ выводимые на Консоль (_info_ для разработчиков);
> - техническое описание основных _Классов_, _API-контроллеров (endpoints)_, _Пакетов_; _*<u>(на момент этого релиза)</u>_


[*v1.0.0 (Pastebin-project MVP)](https://github.com/alibekbirlikbai/Pastebin-project/releases/tag/v1.0.0) (Основная Документация Проекта) <br>
[v1.1.0](https://github.com/alibekbirlikbai/Pastebin-project/releases/tag/v1.1.0) _**(--актуальная версия--)**_


---


## Info по веткам
- _**main**_ - _Production_ ветка, стабильные (завершенные) версии проекта
- _**release**_ - ветка _Тестирования_, подготовка новых релизов к _Production_ (version*)
- _**develop**_ - _Development_ ветка, интеграция всех изменений (Фич) для релиза
- _**feature/***_ - ветка разработки новых функционалов и улучшений


---


## Инструкции для Запуска Проекта

1. Сначала клонируете этот проект через терминал:

```bash
# Clone this repository
$ git clone https://github.com/alibekbirlikbai/Pastebin-project.git
```

2. Потом запускаете _сервер_. Можете сделать это через _IntelliJ IDEA_, или через любой другой удобный для вас _IDE_:

![(intelliji open project)](https://github.com/alibekbirlikbai/Pastebin-project/assets/87764579/efa7bfdd-e92a-4f2d-acec-261ad26e50d9)

> [!NOTE]
> Для тестирования функционала проекта, дальше можете использовать [_postman_](https://www.postman.com/)

3. Как только ваш сервер запустится, используйте этот _URL-адрес_ как основу для построения _API_ запросов `http://localhost:8080/api/v*/pastbin`
   - _**v***_ - замените на версию проекта которую вы клонировали (ориентируйтесь по релизам, например для релиза v1.0.0 версия для API будет как - v1, и т.д.)
   
Через эту ссылку можете зайти в мой [_Postman-workspace_](https://alibek-birlikbai-postman-teamworkspace.postman.co/workspace/Team-Workspace~92bf7d2f-5709-4c66-8ec2-c567f598544f/folder/33454870-54f927a3-d286-471f-b6c8-76fb1f32806f?action=share&creator=33454870&ctx=documentation) 
и протестировать _API_ по версиям (или сделать это вручную)

