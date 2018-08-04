<#import "header.ftl" as c/>
<@c.page title="${event.name}" isprof="0"/>

<ul>
    <li>${event.name!}</li>
    <li>${event.description!}</li>
    <li><a href="${event.link!}">Ссылка на мероприятие</a> </li>
    <li>${event.contacts!}</li>
    <li>head of event <a href="/user/profile/${event.headOfEventUser.email}">${event.headOfEventUser.name}</a></li>

<#if event.members??>
    Участники
    <#list event.members as user>
        <li><a target="_blank" href="/user/profile/${user.email}"> ${user.name}</a></li>
    </#list>
</#if>

    <br>
    <br>

<#--if user is admin of this project will not show apply button-->
<#if isAdminProject??>
<#-- else if user is applicant or not cheking-->
<#elseif isMember??>
    <#if isMember>
        Вы принимаете участие
    <#else >
        <a href="/join/${event.uuid}">Принять участие!</a>
    </#if>
</#if>
</ul>

<#import "footer.ftl" as f/>
<@f.page/>
