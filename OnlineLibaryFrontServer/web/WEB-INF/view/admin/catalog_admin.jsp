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
        <c:if test="${selectedSection != null}">
            <title>${selectedSection.name}</title>
        </c:if>
        <c:if test="${keyword != null}">
            <title>${keyword} - Результаты</title>
        </c:if>
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
                            <li>
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

        <main id="catalog-index">
            <div class="top-content">
                <div class="container">
                    <h1>
                        Найдите свою книгу
                    </h1>
                    <form action="catalog_admin" method="get">
                        <input type="search" name="keyword" placeholder="Поиск по сайту"><input type="submit" value=" ">
                    </form>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="catalog-background-1">
                <div class="catalog-background-2">
                    <div class="middle-content">
                        <nav id="catalog-nav">
                            <h4>Разделы:</h4>
                            <ul>
                                <c:forEach var="section" items="${sections}">
                                    <li <c:if test="${section.id == selectedSection.id}"> class="selected" </c:if> >
                                        <a href="catalog_admin?section=${section.id}" data-hover="${section.name}">${section.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                            <hr>
                            <a href="book_admin" class="add-book-direction">Добавить книгу</a>
                            <hr>
                        </nav>
                        <div class="clear"></div>
                    </div>
                    <div class="bottom-content">
                        <h4>
                            <c:if test="${selectedSection != null}">${selectedSection.name}</c:if>
                            <c:if test="${keyword != null}">Результаты поиска по запросу ${keyword}</c:if>
                            </h4>
                        <c:forEach var="book" items="${books}">
                            <div class="catalog-book">
                                <a href="book_admin?id=${book.id}">
                                    <img src="img/harry_potter.jpg">
                                    <h3>${book.title}</h3>
                                </a>
                                <p>Автор: ${book.author}</p>
                                <p>Цена: ${book.price} руб.</p>
                                <p>Дата публикации: ${book.dateOfPublicaion.day}-${book.dateOfPublicaion.month}-${book.dateOfPublicaion.year}</p>
                                <a href="book_admin?id=${book.id}" class="book-btn">Редактировать</a>
                                <a href="book_admin?id=${book.id}&delete=true" class="book-btn">Удалить</a>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="clear"></div>
                </div>
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
