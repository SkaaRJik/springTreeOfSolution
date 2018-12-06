<#macro page>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>

    <script type="text/javascript">

        function readFileAjax(){
            var radio = $('input[name=select-file]:checked').val();
            $.ajax({
                url: '/loadFile',
                data: ({fileName : radio}),
                success: function (data) {
                    alert(this.value);

                    $('#readedFile').html(data);
                }
            });
        }
    </script>


</head>
<body>
    <#nested>
</body>
</html>
</#macro>