<#import "header.ftl" as c/>
<@c.page title="Добавить навык" isprof="0"/>

<h3>add skills</h3>
<form action="/addSkill" method="post">
    <input type="text" name="skillName">
    <input type="text" name="skillType">
    <input type="submit">
</form>
</body>
</html>
