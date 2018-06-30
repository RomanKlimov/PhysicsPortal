<#import "header.ftl" as c/>
<@c.page title="${project.name}" isprof="0"/>

<ul>
    <li>${project.name!}</li>
    <li>${project.description!}</li>
    <li><a href="${project.link!}">Ссылка на проект</a> </li>
    <li>${project.contacts!}</li>


<#if project.users??>
    Участники
    <#list project.users as user>
        <li><a target="_blank" href="/user/profile/${user.email}"> ${user.name}</a></li>
    </#list>

</#if>
    <a href="/apply/${project.uuid}">Подать заявку!</a>
</ul>

<#import "footer.ftl" as f/>
<@f.page/>
