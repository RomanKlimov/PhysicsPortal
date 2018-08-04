<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Add Event</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/font-awesome.css" rel="stylesheet">
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
                <li><a href="/teams">Команды</a></li>
                <li><a href="/meetups">Мероприятия</a></li>
                <li><a href="/contacts">Контакты</a></li>
                <li><a href="/logout">Выход</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<form action="/addNewEvent" method="post">
    Название мероприятия
    <input name="name" type="text" class="form-control">
    Описание
    <input type="text" name="description" class="form-control">
    Ссылка на pdf файл
    <input type="text" name="link" class="form-control">
    Дата и время("dd-MM-yyyy hh:mm")
    <input type="text" name="date" class="form-control">

    <input type="submit">
</form>
</body>
</html>