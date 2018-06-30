<#import "header.ftl" as c/>
<@c.page title="Контакты" isprof="0"/>

<center>
<div class="container" align="center">
        <div class="form-area">
            <form role="form">
                <br style="clear:both">
                <h3 style="margin-bottom: 25px; text-align: center;">Обратная связь</h3>
                <div class="form-group">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Имя" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="email" name="email" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="mobile" name="mobile" placeholder="Мобильный номер" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="subject" name="subject" placeholder="Тема" required>
                </div>
                <div class="form-group">
                    <textarea class="form-control" type="textarea" id="message" placeholder="Сообщение" maxlength="140" rows="7"></textarea>
                </div>

                <button type="button" id="submit" name="submit" class="btn btn-primary pull-right">Отправить</button>
            </form>
    </div>
</div>
</center>
