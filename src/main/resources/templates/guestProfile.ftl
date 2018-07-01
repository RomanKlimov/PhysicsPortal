<#import "header.ftl" as c/>
<@c.page title="Профиль" isprof="0"/>

<ul>
    <li>${user.name}</li>
    <li>${user.email}</li>
    <li>${user.phoneNumber}</li>
<#if user.skills??>
    <#list user.skills as skill>
        <li>${skill.skillName}</li>
        <li>${skill.skillType}</li>
    </#list>
</#if>
</ul>
<#if user.projects??>

<div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"> проекты</h3>
                </div>
                <div class="panel-body">
                    <#list user.projects as project>
                        <li><a target="_blank" href="/project/${project.uuid}"> ${project.name}</a></li>
                    </#list>


            </div>
        </div>
    </div>
    </div>
</#if>

<#if memberOfProjects??>

<div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"> участник проектов</h3>
                </div>
                <div class="panel-body">

                        <#list memberOfProjects as project>
                            <li><a target="_blank" href="/project/${project.uuid}"> ${project.name}</a></li>
                        </#list>

            </div>
        </div>
    </div>
    </div>
</#if>

</body>
</html>
