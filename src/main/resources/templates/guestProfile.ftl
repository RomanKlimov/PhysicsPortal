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
</body>
</html>
