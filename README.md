# Pastebin-project
inspired by - https://pastebin.com/

_Pastebin_ - приложение в котором пользователи могут создавать обычные _блоки текста_, сохранять их в системе, и делиться этими _ссылками (текстом)_ с другими пользователями по Сети.

![Context Diagram (general_v1 0 0)](https://github.com/alibekbirlikbai/Pastebin-project/assets/87764579/61248e86-bc42-494f-aaba-9780c68a74fe)


---


## Features (by versions)
<details>
  <summary><i>*v1.0.0 (краткое описание: MVP проекта)</i></summary>

- [x] 1. ~~Пользователь может создать блок Текста, и загрузить его в систему;~~
- [x] 2. ~~Система должна генерировать уникальный URL адрес (ссылку) на этот блок текста;~~
- [x] 3. ~~Пользователь может отправить эту ссылку другому пользователю, который перейдя по ней, увидит тот же блок текста;~~

</details>


<details>
  <summary><i>v1.1.0 (краткое описание: доп. функционал)</i></summary>

- [x] 4. ~~Блоки текста и ссылки деактивируются через какое-то время и удаляются из системы;~~
    - [x] [**--EVALUATE--. _*comment: Этот функционал лучше реализовать на стороне клиента_**] (4.1. ~~Пользователь может сам указать когда именно это происходит;~~)
- [x] 5. ~~Можно определять Категорию вводимого блока текста;~~
    - [x] 5.1. ~~Поиск по Категории;~~
- [x] 6. ~~Можно определять Пароли на ссылки;~~
- [x] 7. **[Tech-feature]** ~~Сокрытие всех дополнительных параметров на URL ссылке, пользователь не должен их видеть (все дополнительные параметры должны храниться в БД, и обрабатываться на стороне сервера);~~

</details>


<details open>
  <summary><i>[Upcoming] v1.2.0 (краткое описание: Deployment проекта)</i></summary>

- [ ] 8. **[Tech-feature]** ~~Реализация сохранения контента в настоящее Облако (замена Cloud-Simulation);~~
- [ ] 9. **[Tech-feature]** Реализация простого Front-end (Angular/TypeScript), чисто чтобы визуально продемонстрировать функционал проекта; 
  - _(код для Front-end в [отдельном репозитории](https://github.com/alibekbirlikbai/Pastebin-frontend))_
- [ ] 10. **[Tech-feature]** Deployment проекта в Интернете;

</details>


---


## Project versions
> [!NOTE]
> Рекомендую сначало ознакомится с [_v1.0.0_](https://github.com/alibekbirlikbai/Pastebin-project/releases/tag/v1.0.0)
> <br> В этом релизе был описан:
> - основной _workflow_ проекта;
> - принципы работы _API (+user experience)_;
>   - _Logs_ выводимые на Консоль (_info_ для разработчиков);
> - техническое описание основных _Классов_, _API-контроллеров (endpoints)_, _Пакетов_; _*<u>(на момент этого релиза)</u>_

1. [v1.0.0](https://github.com/alibekbirlikbai/Pastebin-project/releases/tag/v1.0.0) (Основная Документация Проекта) <br>
2. [v1.1.0](https://github.com/alibekbirlikbai/Pastebin-project/releases/tag/v1.1.0) _**(--актуальная версия--)**_


---


## Branch info
> [!NOTE]
> Наиболее актуальную версию проекта (но еще не выпущенную в релиз), можно посмотреть на ветках - _**develop**_ / _**feature***_

- _**main**_ - _Production_ ветка, стабильные (завершенные) версии проекта
- _**release**_ - ветка _Тестирования_, подготовка новых релизов к _Production_ (version*)
- _**develop**_ - _Development_ ветка, интеграция всех изменений (Фич) для релиза
- _**feature/***_ - ветка разработки новых функционалов и улучшений


---


## Инструкции для запуска

1. **Клонирование** проекта
    ```bash
    $ git clone https://github.com/alibekbirlikbai/Pastebin-project.git
    ```

2.  <details>
    <summary id="">Настройка <b><i>Базы Данных</i></b> (БД)</summary>

    - В _PostgreSQL_ создайте _БД_: `PasteBin_Project`
    - Затем в `application.properties` введите - _username_, _password_ от своего _PostgreSQL_ аккаунта
      ![db (config)](https://github.com/alibekbirlikbai/Pastebin-project/assets/87764579/9eae5659-6b41-4d48-bf25-28fd7fee13a6)
    
    </details>


3. <details>
    <summary id="">Настройка <i><b>Сервера</b> (+ описание API endpoints)</i></summary>

   - (пример запуска _Сервера_ на _IntelliJ IDEA_):
     ![(intelliji open project)](https://github.com/alibekbirlikbai/Pastebin-project/assets/87764579/efa7bfdd-e92a-4f2d-acec-261ad26e50d9)
   - Как только _Сервер_ запустится, используйте этот _URL-адрес_ как основу для построения _API_ запросов `http://localhost:8080/api/v*/pastbin`
       - _**v***_ - замените на версию проекта которую вы клонировали (ориентируйтесь по релизам, например для релиза v1.0.0 версия для API будет как - v1, и т.д.)
       
   </details>


> [!NOTE]
> Все **_endpoints_** определены в [Release Notes](https://github.com/alibekbirlikbai/Pastebin-project?tab=readme-ov-file#project-versions) _(документации)_ для каждой версии проекта <br>
> _*(Для тестирования функционала можете использовать [_postman_](https://www.postman.com/))_