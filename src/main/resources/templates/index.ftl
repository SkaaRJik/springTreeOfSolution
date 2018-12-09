<#import "parts/common.ftl" as c>

<@c.page>
    <form id="file-form" method="post" enctype="multipart/form-data" action="/upload">
        <div class="file-uploader">
            <label for="custom-file-upload" class="filupp">
                <span class="filupp-file-name js-value">Загрузить файл</span>
                <input type="file" name="attachment-file" value="1" id="custom-file-upload" accept=".text/csv">
            </label>
        </div>
    </form>
    <#if message??><div>${message}</div></#if>
    <#if files?has_content>

    <div id="table-list-files">
        <table class="container">
            <caption><b>Список входных данных:</b></caption>
            <thead>
            <tr>
                <th>Имя файла</th>
            </tr>
            </thead>
        <tbody>
    </#if>



    <#list files as file>
        <tr><td id="${file}" onClick="chooseFile(this)">${file}</td></tr>
    <#else>
            No files
    </#list>
    <#if files?has_content>
            </tbody>
        </table>
    </div>

    <div>
        <div id="readedFile"></div>
    </div>
    <script type="text/javascript">

        var lastID;


        function readFileAjax(){
            var radio = lastID;
            $.ajax({
                url: '/loadFile',
                data: ({fileName : radio}),
                success: function (data) {
                    $("#table-list-files").append("<button id=\"buildTree\">Построить</button>")
                    $('#readedFile').html(data);
                    $("#buildTree").click(function(){
                        goToBuild();
                    });
                }
            });
        };

        function goToBuild(){
            var href = "/build?fileName="
            href += lastID;
            window.location.replace(href);
        };

        $(document).ready(function(){

            $('input[type="file"]').change(function(){
                //var myRe = new RegExp("[\w-]+\.csv");
                var value = $("input[type='file']").val().match("[\\w-]+\\.csv");
                $('.js-value').text(value);
                $("#file-form").submit();

            });
        });

        function chooseFile(cell) {
            lastID = cell.id;
            if(lastID) {
                readFileAjax()
            }
        }
    </script>


    </#if>

</@c.page>

