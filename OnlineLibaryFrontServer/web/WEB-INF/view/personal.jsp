<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
        <link href="css/style.css" rel="stylesheet">
        <link href="css/_normalize.css" rel="stylesheet">
        <title>Личный кабинет</title>
    </head>
    <body>

        <header>
            <div id="header-nav">
                <div class="container">
                    <div class="header-logo">
                        <a href="main">
                            <img src="img/logo.png" alt="">
                        </a>
                    </div>
                    <nav id="main-nav">
                        <ul id="nav">
                            <li>
                                <a href="main">Главная</a>
                            </li>
                            <li>
                                <a href="catalog">Каталог</a>
                            </li>
                            <li class="enter">
                                <a id="toggle-link-user" href="#" >
                                    <img src="img/user.png">
                                    Пользователь
                                </a>
                                <div class="hidden-user">
                                    <img src="img/user.png">
                                    <p>${user.firstName} ${user.secondName}</p>
                                    <p>${user.email}</p>
                                    <form action="login" method="post" class="hidden">
                                        <input type="hidden" name="logout" value="true">
                                        <input type="submit" value="Выйти">
                                    </form>
                                    <a href="personal">Мой кабинет</a>
                                </div>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="clear"></div>
            </div>
        </header>

        <main id="office-index">
            <div class="top-content">
                <div class="container">
                    <h1>
                        Ваш личный кабинет
                    </h1>
                    <h2>
                        ${user.firstName} ${user.secondName}
                    </h2>
                </div>
            </div>
            <div class="tabs">
                <input id="tab1" type="radio" name="tabs" checked>
                <label for="tab1">Купленные книги</label>

                <input id="tab2" type="radio" name="tabs">
                <label for="tab2">История отзывов</label>

                <section id="content1">
                    <c:forEach var="book" items="${books}">
                        <div class="my-book">
                            <img src="img/harry_potter.jpg">
                            <div class="my-book-text">
                                <p>Название: <span>${book.title}</span></p>
                                <p>Автор: <span>${book.author}</span></p>
                                <p>Цена: <span>${book.price}руб.</span></p>
                                <a href="book?id=${book.id}" class="book-href">Просмотреть</a>
                            </div>
                        </div>
                    </c:forEach>
                </section>
                <section id="content2">
                    <c:forEach var="commentWithUser" items="${commentsWithUsers}">
                        <div class="book-comment">
                            <h3>${commentWithUser.value.title}</h3>
                            <p>${commentWithUser.key.date.day}-${commentWithUser.key.date.month}-${commentWithUser.key.date.year}</p>
                            <p class="limit-text">${commentWithUser.key.content}</p>
                            <a href="book?id=${commentWithUser.value.id}">Смотреть далее...</a>
                        </div>
                    </c:forEach>
                </section>
            </div>
        </main>

        <footer>
            <div id="main-footer">
                <div class="container">
                    <div class="footer-content">
                        <h5>разделы книг</h5>
                        <div class="footer-hr"></div>
                        <ul class="footer-ul">
                            <c:forEach var="section" items="${sections}">
                                <li>
                                    <a href="catalog?section=${section.id}">${section.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="footer-content">
                        <h5>информация</h5>
                        <div class="footer-hr"></div>
                        <ul class="footer-ul">
                            <li>
                                <a href="main">главная</a>
                            </li>
                            <li>
                                <a href="catalog">каталог</a>
                            </li>
                        </ul>
                    </div>
                    <div class="footer-content">
                        <h5>контакты</h5>
                        <div class="footer-hr"></div>
                        <ul class="footer-ul">
                            <li>
                                <span>+375-29-357-32-22</span>
                            </li>
                            <li>
                                <p>E-mail: revenokanton@gmail.com</p>
                            </li>
                        </ul>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div id="copyright-footer">
                <div class="container">
                    <p>@<span id="copyright-year"> </span> Махонь, Костевич, Ревенок</p>
                </div>
            </div>
        </footer>

    </body>

    <!--[if lt IE 9]>
    <script src="http://css3-mediaqueries-js.googlecode.com/files/css3-mediaqueries.js"></script>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/script.js"></script>
</html>
