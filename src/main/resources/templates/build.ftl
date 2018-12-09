<#import "parts/common.ftl" as c>

<@c.page>
    <div id="container"> </div>

    <script type="text/javascript">

        function drawTree() {
            /*sigma.parsers.json('/build/get_tree', {
                container: 'container',
                settings: {
                    defaultNodeColor: '#ec5148'
                }
            });*/
            $.getJSON( "/build/tree.json", function( data ) {

                var element = document.getElementById('container');
                var positionInfo = element.getBoundingClientRect();
                var height = positionInfo.height;
                var width = positionInfo.width;
                g = {
                    nodes: [],
                    edges: []
                };
                jsonNodes = data.nodes;
                var i = 1;
                var previousLevel = -1;
                $.each(jsonNodes, function(index, value){

                    g.nodes.push({
                        id: value.id,
                        label: value.attribute + " " + value.label + " " + value.examples,
                        x: Math.cos(Math.PI * 2 * i / jsonNodes.length) * value.level,
                        y: Math.sin(Math.PI * 2 * i / jsonNodes.length),
                        size: 3
                    });
                    i++;
                });
                jsonEdges = data.edges;
                $.each(jsonEdges, function(index, value){
                    g.edges.push({
                        id: value.id,
                        source : value.source,
                        target : value.target
                    });
                });
                s = new sigma({
                    graph: g,
                    container: 'container',
                    settings: {
                        defaultLabelColor: '#fff',
                        sideMargin: 2,
                        defaultNodeColor: '#ec5148'
                    }
                });




                s.startForceAtlas2({
                    gravity : 0.1,
                });


            });
        }



        function getStatusOfBuilding(thread){

            $.ajax({
                url: '/build/status',
                success: function (data) {
                    NProgress.set(data/100);
                    if(data == 100){
                        clearInterval(thread);
                        NProgress.done();
                        drawTree();
                    }
                },
                error: function (data) {
                    clearInterval(thread);
                }

            });
        }

        $(document).ready(function () {
            NProgress.start();
            var thread = setInterval(frame, 10);
            function frame() {
                getStatusOfBuilding(thread)
            }
        });
    </script>


</@c.page>

