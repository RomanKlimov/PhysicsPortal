<#import "header.ftl" as c/>
<@c.page title="${project.name}" isprof="0"/>

<ul>
    <li>${project.name!}</li>
    <li>${project.description!}</li>
    <li><a href="${project.link!}">Ссылка на проект</a> </li>
    <li>${project.contacts!}</li>
    <li>head of project <a href="/user/profile/${project.headOfProjectUser.email}">${project.headOfProjectUser.name}</a></li>

    <#if project.members??>
        Участники
        <#list project.members as user>
            <li><a target="_blank" href="/user/profile/${user.email}"> ${user.name}</a></li>
        </#list>
    </#if>

    <br>
    <br>
    <#if project.members?? && isAdminProject??>
        Заявки
        <#list project.applicants as user>
            <li>${user.name} <a href="/project/${project.uuid}/${user.email}/accept">Принять</a> <a href="/project/${project.uuid}/${user.email}/decline">Отказать</a></li>
        <#else >
        </#list>
    </#if>

    <#--if user is admin of this project will not show apply button-->
    <#if isAdminProject??>
    <#-- else if user is applicant or not cheking-->
    <#elseif isApplicant??>
        <#if isApplicant>
            Заявка отправлена
        <#else >
            <a href="/apply/${project.uuid}">Подать заявку!</a>
        </#if>
    </#if>
</ul>

<#import "footer.ftl" as f/>
<@f.page/>
