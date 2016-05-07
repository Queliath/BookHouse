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
        <title>Администрирование</title>
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

        <ul class="breadcrumbs">
            <li>
                <a href="admin">Администрирование</a>
            </li>
            <li>
                <span>Раздел</span>
            </li>
            <div class="clear"></div>
        </ul>
        <main id="registartion-index">
            <div class="top-content">
                <div class="container">
                    <div class="registration">
                        <h3>Раздел</h3>
                        <form action="" method="post">
                            <label>Название:<input type="text" placeholder="Как называется раздел?" maxlength="30" name="name" value="${section.name}"></label>
                            <label>Описание:<input type="text" placeholder="Напишите его описание" name="description" value="${section.description}"></label>
                            <label>Картика:<input type="text" placeholder="URL картинки" name="image" value="${section.image}"></label>
                            <input type="submit" value="Сохранить">
                        </form>
                    </div>
                    <c:if test="${updateSuccess}">
                        <div class="registration-successful">
                            <p>Раздел успешно обновлен!</p>
                        </div>
                    </c:if>
                    <c:if test="${addSuccess}">
                        <div class="registration-successful">
                            <p>Раздел успешно добавлен!</p>
                        </div>
                    </c:if>
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
