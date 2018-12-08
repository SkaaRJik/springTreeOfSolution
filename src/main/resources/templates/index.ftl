<#import "parts/common.ftl" as c>

<@c.page>
    <form method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <button type="submit">Добавить</button>
    </form>
    <#if message??><div>${message}</div></#if>
    <#if files??>

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
    <button id="buildTree">Построить!!!!</button>
    <div>
        <div id="readedFile"></div>
    </div>
    <script type="text/javascript">
        function readFileAjax(){
            var radio = $('input[name=select-file]:checked').val();
            $.ajax({
                url: '/loadFile',
                data: ({fileName : radio}),
                success: function (data) {
                    $('#readedFile').html(data);
                }
            });
        };

        function goToBuild(){
            var href = "/build?fileName="
            href += $('input[name=select-file]:checked').val();
            window.location.replace(href);
        };

        $(document).ready(function(){
            $('input[name="select-file"]:radio').change(function(){
                readFileAjax();
            });
            $("#buildTree").click(function(){
                goToBuild();
            });
        });
    </script>


    </#if>

</@c.page>

