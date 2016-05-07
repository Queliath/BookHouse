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
        <title>BookHouse - Элекронная библиотека</title>
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
                            <li class="active">
                                <a href="main">Главная</a>
                            </li>
                            <li>
                                <a href="catalog">Каталог</a>
                            </li>
                            <li class="enter">
                                <c:if test="${user == null}">
                                    <a id="toggle-link" href="#" >
                                        <img src="img/login.png">
                                        Вход
                                    </a>
                                    <div class="hidden-login">
                                        <form action="login" method="post" class="hidden">
                                            <input type="email" placeholder="Email" maxlength="50" name="email">
                                            <input type="password" placeholder="Пароль" maxlength="30" name="password">
                                            <input type="submit" value="Войти">
                                        </form>
                                        <a href="registry">Зарегистрироваться</a>
                                    </div>
                                </c:if>
                                <c:if test="${user != null}">
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
                                </c:if>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="clear"></div>
            </div>
        </header>
        <main id="main-index">
            <div class="top-content">
                <div class="container">
                    <h1>
                        Электронная библиотека
                    </h1>
                    <h2>
                        Book House
                    </h2>
                    <form action="catalog" method="get">
                        <input type="search" name="keyword" placeholder="Поиск по сайту"><input type="submit" value=" ">
                    </form>
                </div>
                <div class="clear"></div>
            </div>
            <div class="middle-content">
                <div class="container">
                    <c:forEach var="section" items="${sections}">
                        <div class="catalog">
                            <a href="catalog?section=${section.id}">
                                <div class="icon fa-book"></div>
                                <h2>${section.name}</h2>
                            </a>
                            <p>Совокупность письменных трудов, которые созданы в результате исследований, теоретических обобщений, сделанных в рамках научного метода.</p>
                        </div>
                    </c:forEach>
                </div>
                <div class="clear"></div>
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
