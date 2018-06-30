<#import "header.ftl" as c/>
<@c.page title="Поиск" isprof="0"/>


<center>
    <img src="/img/sky.png" style="width: 250px">
<form method="post" action="/search" class="form-inline">
<input class="form-control mr-sm-2" type="search" placeholder="Поиск" aria-label="Search" name="input" >
<input class="btn btn-outline-success my-2 my-sm-0" type="submit"  value="search">
</form>

    <#if users??>
<#list users as user>
    <#--<li><a target="_blank" href="/user/profile/${user.email}"> ${user.name}</a></li>-->
    <div class="profile" style="
    align-content:  center;
    width:  50%;
">
        <h1 class="page-header"></h1>

        <div class="row">
            <div class="col-md-4">

                <img src="/img/user.png" class="img-thumbnail" alt="" style="width:  100px;">
                <h3>${user.name}</h3>
                <h3>Иванов</h3>
            </div>

            <div class="col-md-8">
                <ul style="list-style-type: none ">

                    <li><strong>Город:</strong>Тверь</li>
                    <#list user.skills as skill>
                        <ul style="list-style-type: none ">
                            <li><strong>Навык:</strong>${skill.skillName}</li>
                        </ul>
                    </#list>
                </ul>
                <a href="user/profile/${user.email}" class="btn btn-primary">Подробнее</a>
            </div>

        </div><br><br>
        <div class="row">
            <div class="col-md-12" style="
    margin-top:  -5%;
">
                <div class="panel panel-default">


                </div>
            </div>
            </div>
        </div>
    </div>
</#list>

</#if>

</center>
</body>
</html>
