<table style="background: gainsboro;border: black solid 1px">
    <tr>
        <td style="background: yellow;border: black solid 1px;padding: 10px;" colspan="2">010-server</td>
    </tr>
    <tr>
        <td style="background: yellow;border: black solid 1px;padding: 10px;">config prop</td>
    </tr>
    <tr>
        <td >Start with profile</td>
        <td >mvn spring-boot:run -Dspring.profiles.active=$NAME_PROFILE$</td>
    </tr>
    <tr>
        <td >Secretkey</td>
        <td >Y2xpZW50X2d1aWxkX29mX3RlYWNoZXJzLmNvbTpzZWNyZXRfMDEwc2VydmVyLmNvbQ==</td>
    </tr>
    <tr>
        <td >db.username dev</td>
        <td>root</td>
    </tr>
    <tr>
        <td >db.password dev</td>
        <td >1234</td>
    </tr>
    <tr>
        <td >websocket</td>
    </tr>
    <tr>
        <td >url connection</td>
        <td >/socket</td>
    </tr>
    <tr>
        <td >url subscribe</td>
        <td >/chat.$ID_CHAT_ROOM$</td>
    </tr>
    <tr>
        <td >url send</td>
        <td >/send/message/$ID_CHAT_ROOM$    -   {METHOD POST}</td>
    </tr>
</table>
<table>
<tr>
<td>
tutorial 
</td>
</tr>
    <tr>
<td>send JSON + MultipartFile</td>
    </tr>
      <tr>
    <td>let formData = new FormData();
    <br>
        formData.appened("files",JSON.stringify(json));
    <br>
        formData.appened(form.files[0]);
    <br>
        http.post("/save",formData);
    <br>
        </td>
        </tr>
</table>"# GuildOfTeachersServer" 
