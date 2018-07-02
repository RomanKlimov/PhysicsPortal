<#import "header.ftl" as c/>
<@c.page title="${user.name}" isprof="1"/>


<form id="form_validation" enctype="multipart/form-data" action="/addPhoto" method="POST" novalidate="novalidate">
    <p><input type="file" name="file" data-max-file-size="2mb" data-default-file=""/></p>
    <p><button type="submit">Добавить фото</button></p>
</form>

<section>
  <div class="container">
    <div class="row">
      <div class="col-md-8">
        <div class="profile">
          <h1 class="page-header">${user.name}</h1>
          <div class="row">
            <div class="col-md-4">

                <img class="img-thumbnail" src="/photo/${user.imageUrl!}" alt="img/user.png" style="
                        max-width:  300px;
                        max-height:  300px;
                        min-width:  300px;
                        min-height:  300px;
                        width: 61px;">
              <#--<img src="img/user.png" class="img-thumbnail" alt="">-->

            </div>
            <#--<form id="sign_up" action="/updateProfile" method="POST" novalidate="novalidate">-->

              <div class="input-group"  width="100px">
                <span class="input-group-addon">
                  <i class="material-icons">Имя</i>
                </span>
                <div class="form-line"  width="100px">
                  <input type="text" class="form-control" name="name" value="${user.name}" placeholder="Имя "  disabled>
                </div>
              </div>

              <div class="input-group" width="100px" >
                <span class="input-group-addon">
                  <i class="material-icons">email</i>
                </span>
                <div class="form-line"  width="100px">
                  <input type="email" class="form-control" name="email" value="${user.email}" placeholder="e-mail" disabled>
                </div>
              </div>
              <div class="input-group"  width="100px">
                <span class="input-group-addon">
                  <i class="material-icons">Телефон</i>
                </span>
                <div class="form-line"  width="100px">
                  <input type="text" class="form-control" name="phoneNumber" value="${user.phoneNumber}" placeholder="Телефон номер" disabled>
                </div>
              </div>


            <#--</form>-->
          </div><br><br>
          <div class="row">
            <div class="col-md-12">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <h3 class="panel-title">Мои проекты</h3>
                </div>
                <div class="panel-body">
                  <#if user.projects??>
                                      <#list user.projects as project>
                                        <li><a target="_blank" href="/project/${project.uuid}"> ${project.name}</a></li>
                                      </#list>

                                      </#if>

                                      <form action="/addNewProject">
                                        <div class="form-group">
                                        </div>
                                        <button type="submit" class="btn btn-default">Добавить проект</button>
                                      </div>
                                      </form>
                                      </div>
                                      </div>
                                      </div>
                                      </div>
                                      </div>
                                      </div>
                                      </div>
                                      </div>
                                      </section>

                                      <div class="profile-box">
                                        <div class="card">
                                          <div class="body">      
                                            <table class="table table-bordered table-striped table-hover js-basic-example dataTable">
                                              <thead>
                                                <tr>
                                                  <th>Название навыка</th>
                                                  <th>Тип навыка</th>
                                                </tr>
                                                <tbody>
                                                  <#list user.skills as skill>
                                                    <tr>
                                                      <td >${skill.skillName}</td>
                                                      <td >${skill.skillType}</td>
                                                    </tr>
                                                  </#list>
                                                </tbody>

                                          </div>
                                        </div>
                                      </div>
                                      <div>
                                        <input type="text" id="autocomplete"/>
                                        <input type="button" value="Добавить навык" onclick="addSkill()">
                                      </div>
                                      <script>
                                        $(function () {

                                            $("#autocomplete").autocomplete({
                                                source: "/getSkills",
                                                minLength: 1
                                            });
                                        });
                                        function addSkill() {
                                            var skillName = $('#autocomplete');
                                            if (skillName.val().length>0) {
                                                $.ajax({
                                                    method: 'POST',
                                                    url: '/addSkillToUser',
                                                    dataType: 'json',
                                                    data: {skillName: skillName.val()},
                                                    success: function () {
                                                        console.log("go")
                                                    }
                                                });
                                            }
                                        }
                                      </script>
