<#import "parts/common.ftl" as c>

<@c.page>
    <div>
        <div id="status"></div>
    </div>
    <script type="text/javascript">
        function getStatusOfBuilding(tread){

            $.ajax({
                url: '/build/status',
                success: function (data) {
                    $('#status').html(data);


                    if(data == 100){
                        clearInterval(tread);
                        NProgress.set(0.4);
                    }
                }
            });
        }
    </script>
    <script>
        $(document).ready(function () {
            NProgress.start();
            var tread = setInterval(frame, 10);
            function frame() {
                getStatusOfBuilding(tread)
            }
        });
    </script>


</@c.page>

