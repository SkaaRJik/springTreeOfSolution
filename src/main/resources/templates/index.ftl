<#import "parts/common.ftl" as c>

<@c.page>
    <form method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <button type="submit">Добавить</button>
    </form>
    <#if message??><div>${message}</div></#if>
    <#if files??>
    <script>
        $(document).ready(function(){
            $('input[name="select-file"]:radio').change(function(){

                readFileAjax();
            });
        });
    </script>
    </script>
    <div id="table-list-files">
            <table>
            <caption><b>Список входных данных:</b></caption>
    <thead>
    <tr>
    <th>Выбор</th> <th>Имя файла</th>
                </tr>
            </thead>
            <tbody>
    </#if>



    <#list files as file>
        <tr><td><input type="radio" name="select-file" value="${file}"></td><td>${file}</td></tr>
    <#else>
            No files
    </#list>
    <#if files??>
            </tbody>
        </table>
    </div>
    <div>
        <div id="readedFile"></div>
            </div>

    </#if>

</@c.page>

