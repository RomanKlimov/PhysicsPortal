<#macro page isprof title>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${title}</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/font-awesome.css" rel="stylesheet">

    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&coordorder=longlat" type="text/javascript"></script>
    <script src="/js/placemark.js" type="text/javascript"></script>
      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
      <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <style>
      html, body, #map {
          width: 100%; height: 500px; padding: 0; margin: 0;
      }
    </style>
  </head>

  <body>
      <nav class="navbar navbar-default">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
                <li class="active"><a href="/index">Главная</a></li>
                <li><a href="/search">Поиск</a></li>
                <li><a href="/teams">Проекты</a></li>
                <li><a href="/meetups">Мероприятия</a></li>
                <li><a href="/contacts">Контакты</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right" align="right">
            <#if isprof == "1">
              <li><a class="navbar-brand mx-auto" href="/profile">Профиль</a></li>
            <#else>
            <li><a class="navbar-brand mx-auto" href="/signUp1">Регистрация</a></li>
            <li><a class="navbar-brand mx-auto" href="/login">Вход</a></li>
            </#if>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
</#macro>
