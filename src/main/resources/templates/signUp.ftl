<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Dobble Social Network: Profile Page</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="static/css/style.css" rel="stylesheet">
    <link href="static/css/font-awesome.css" rel="stylesheet">
</head>
<body>
<div class="signup-box">
    <div class="card">
        <div class="body">
            <form id="sign_up" action="/signUp" method="POST" novalidate="novalidate">

                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">Имя</i>
                        </span>
                    <div class="form-line">
                        <input type="text" class="form-control" name="name" placeholder="Имя " required="" autofocus="" aria-required="true">
                    </div>
                </div>
                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">Фамилия</i>
                        </span>
                    <div class="form-line">
                        <input type="text" class="form-control" name="lastName" placeholder="Фамилия " required="" autofocus="" aria-required="true">
                    </div>
                </div>
                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">email</i>
                        </span>
                    <div class="form-line">
                        <input type="email" class="form-control" name="email" placeholder="e-mail" required="" aria-required="true">
                    </div>
                </div>
                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">Телефон</i>
                        </span>
                    <div class="form-line">
                        <input type="text" class="form-control" name="phoneNumber" placeholder="Телефон номер" required="" aria-required="true">
                    </div>
                </div>
                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">Пароль</i>
                        </span>
                    <div class="form-line">
                        <input type="password" class="form-control validate-equalTo-blur" name="password" minlength="6" placeholder="Пароль" required="" aria-required="true" aria-invalid="false">
                    </div>
                </div>
                <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">Повтор пароля</i>
                        </span>
                    <div class="form-line">
                        <input type="password" class="form-control" name="confirm" minlength="6" placeholder="Введите еще раз пароль" required="" aria-required="true" aria-invalid="true">
                    </div>
                </div>
                <button class="btn btn-block btn-lg bg-pink waves-effect" type="submit">Зарегистрироваться</button>

                <div class="m-t-25 m-b--5 align-center">
                    <a href="/login">Уже зарегистрированы?</a>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>