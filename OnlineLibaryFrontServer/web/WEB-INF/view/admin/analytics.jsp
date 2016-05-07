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
        <title>Аналитика</title>
    </head>
    <body>

        <header>
            <div id="header-nav">
                <div class="container">
                    <div class="header-logo">
                        <a href="admin">
                            <img src="img/logo.png" alt="">
                        </a>
                    </div>
                    <nav id="main-nav">
                        <ul id="nav">
                            <li>
                                <a href="admin">Администирование</a>
                            </li>
                            <li class="active">
                                <a href="analytics">Аналитика</a>
                            </li>
                            <li class="enter">
                                <a id="toggle-link-user" href="#" >
                                    <img src="img/user.png">
                                    Адмиинистратор
                                </a>
                                <div class="hidden-user">
                                    <img src="img/user.png">
                                    <p>Адмиинистратор</p>
                                    <p>${user.email}</p>
                                    <form action="login" method="post" class="hidden">
                                        <input type="hidden" name="logout" value="true">
                                        <input type="submit" value="Выйти">
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="clear"></div>
            </div>
        </header>

        <main id="analytics-index">
            <div class="top-content">
                <div class="container">
                    <h1>
                        Топ книг
                    </h1>
                    <h2>
                        Book House
                    </h2>
                </div>
            </div>
            <div class="tabs">
                <input id="tab1" type="radio" name="tabs" checked>
                <label for="tab1">По рейтингу</label>

                <input id="tab2" type="radio" name="tabs">
                <label for="tab2">По комментариям</label>

                <input id="tab3" type="radio" name="tabs">
                <label for="tab3">По покупкам</label>

                <input id="tab4" type="radio" name="tabs">
                <label for="tab4">Статистика покупок</label>

                <section id="content1">
                    <c:forEach var="topBook" items="${topBooksByRating}">
                        <div class="my-book">
                            <img src="img/harry_potter.jpg">
                            <div class="my-book-text">
                                <p>Название: <span>${topBook.key.title}</span></p>
                                <p>Автор: <span>${topBook.key.author}</span></p>
                                <p>Рейтинг: <span>${topBook.value}</span></p>
                                <a href="book?id=${topBook.key.id}" class="book-href">Просмотреть</a>
                            </div>
                        </div>
                    </c:forEach>
                </section>
                <section id="content2">
                    <c:forEach var="topBook" items="${topBooksByComment}">
                        <div class="my-book">
                            <img src="img/harry_potter.jpg">
                            <div class="my-book-text">
                                <p>Название: <span>${topBook.key.title}</span></p>
                                <p>Автор: <span>${topBook.key.author}</span></p>
                                <p>Кол-во комментариев: <span>${topBook.value}</span></p>
                                <a href="book?id=${topBook.key.id}" class="book-href">Просмотреть</a>
                            </div>
                        </div>
                    </c:forEach>
                </section>
                <section id="content3">
                    <c:forEach var="topBook" items="${topBooksByPurchase}">
                        <div class="my-book">
                            <img src="img/harry_potter.jpg">
                            <div class="my-book-text">
                                <p>Название: <span>${topBook.key.title}</span></p>
                                <p>Автор: <span>${topBook.key.author}</span></p>
                                <p>Кол-во покупок: <span>${topBook.value}</span></p>
                                <a href="book?id=${topBook.key.id}" class="book-href">Просмотреть</a>
                            </div>
                        </div>
                    </c:forEach>
                </section>
                <section id="content4">
                    <h2>Данные для графика покупок по разделам</h2>
                    <ul>
                        <c:forEach var="point" items="${sectionsWithPurchases}">
                            <li>${point.key.name} - ${point.value}</li>
                            </c:forEach>
                    </ul>
                    <h2>Данные для графика покупок по ценам</h2>
                    <ul>
                        <c:forEach var="point" items="${purchasesByPriceChart}">
                            <li>${point}</li>
                            </c:forEach>
                    </ul>
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
                                    <a href="#">${section.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="footer-content">
                        <h5>информация</h5>
                        <div class="footer-hr"></div>
                        <ul class="footer-ul">
                            <li>
                                <a href="admin">Администрирование</a>
                            </li>
                            <li>
                                <a href="analytics">Аналитика</a>
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
