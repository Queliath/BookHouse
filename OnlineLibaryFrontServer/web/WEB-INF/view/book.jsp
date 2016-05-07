<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
        <link href="css/star-rating-svg.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/_normalize.css" rel="stylesheet">
        <title>${selectedBook.title}</title>
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

        <main id="book-index">
            <div class="catalog-background-1">
                <ul class="breadcrumbs">
                    <li>
                        <a href="catalog">Каталог</a>
                    </li>
                    <li>
                        <span>${selectedBook.title}</span>
                    </li>
                </ul>
                <div class="clear"></div>
                <div class="catalog-background-2">
                    <div class="top-content">
                        <div class="book">
                            <img src="img/harry_potter.jpg">
                            <c:if test="${user != null}">
                                <div class="rating" data-bookId="${selectedBook.id}" data-userId="${user.id}"></div>
                            </c:if>
                            <p>Рейтинг: <span class="ratingValue">${averageRating}</span></p>
                                <c:if test="${user != null}">
                                <div class="download">
                                    <c:if test="${alreadyBought}">
                                        <a href="files/${selectedBook.fileName}">Скачать</a>
                                    </c:if>
                                    <c:if test="${alreadyBought == null}">
                                        <a href="purchase?book=${selectedBook.id}">Купить</a>
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                        <div class="book-description">
                            <h2>${selectedBook.title}</h2>
                            <span>Автор:</span>
                            <p>${selectedBook.author}</p>
                            <span>Описание:</span>
                            <p>${selectedBook.description}</p>
                            <span>Цена:</span>
                            <p>${selectedBook.price} руб.</p>
                            <span>Аннотация:  </span>
                            <div class="clear"></div>
                            <p>${selectedBook.preview}</p>
                        </div>
                    </div>
                    <div class="hr"></div>
                    <div class="middle-content">
                        <div class="comment">
                            <c:if test="${user == null}">
                                <div class="comment-login">
                                    <span>Чтобы оставить комментарий</span> <a href="#" class="to-top">войдите</a> <a href="#" class="to-top-mobile">войдите</a> <span>или</span> <a href="registry">зарегестрируйтесь</a>.
                                </div>
                            </c:if>
                            <c:if test="${user != null}">
                                <div class="comment-send">
                                    <h4>Оставьте комментарий:</h4>
                                    <form action="comment" method="post">
                                        <input type="hidden" name="bookId" value="${selectedBook.id}">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <input type="text" name="content">
                                        <input type="submit" class="send-btn" value="Отправить">
                                    </form>
                                </div>
                            </c:if>
                            <div class="comments">
                                <h4>Комментарии(${numberOfComments})</h4>
                                <c:forEach var="commentWithUser" items="${commentsWithUsers}">
                                    <hr>
                                    <span class="comment-user">${commentWithUser.value.firstName} ${commentWithUser.value.secondName}</span>, <span>${commentWithUser.key.date.day}-${commentWithUser.key.date.month}-${commentWithUser.key.date.year}</span>
                                    <p>${commentWithUser.key.content}</p>
                                    <hr>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
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
    <script src="js/jquery.star-rating-svg.js"></script>
    <script>
        $(".rating").starRating({
        <c:if test="${rating != null}">
            initialRating: ${rating.value},
        </c:if>
        <c:if test="${rating == null}">
            initialRating: 0,
        </c:if>
        starSize: 13,
        totalStars: 10,
        useFullStars: true
        });
    </script>
    <script src="js/script.js"></script>
</html>
