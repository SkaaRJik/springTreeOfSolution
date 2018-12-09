<#macro page>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <script src='https://unpkg.com/nprogress@0.2.0/nprogress.js'></script>
    <link rel="stylesheet" href="https://unpkg.com/nprogress@0.2.0/nprogress.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <script type="text/javascript" src="js/sigma/src/sigma.core.js"></script>
    <script type="text/javascript" src="js/sigma/src/conrad.js"></script>
    <script type="text/javascript" src="js/sigma/src/utils/sigma.utils.js"></script>
    <script type="text/javascript" src="js/sigma/src/utils/sigma.polyfills.js"></script>
    <script type="text/javascript" src="js/sigma/src/sigma.settings.js"></script>
    <script type="text/javascript" src="js/sigma/src/classes/sigma.classes.dispatcher.js"></script>
    <script type="text/javascript" src="js/sigma/src/classes/sigma.classes.configurable.js"></script>
    <script type="text/javascript" src="js/sigma/src/classes/sigma.classes.graph.js"></script>
    <script type="text/javascript" src="js/sigma/src/classes/sigma.classes.camera.js"></script>
    <script type="text/javascript" src="js/sigma/src/classes/sigma.classes.quad.js"></script>
    <script type="text/javascript" src="js/sigma/src/classes/sigma.classes.edgequad.js"></script>
    <script type="text/javascript" src="js/sigma/src/captors/sigma.captors.mouse.js"></script>
    <script type="text/javascript" src="js/sigma/src/captors/sigma.captors.touch.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/sigma.renderers.canvas.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/sigma.renderers.webgl.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/sigma.renderers.svg.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/sigma.renderers.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/webgl/sigma.webgl.nodes.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/webgl/sigma.webgl.nodes.fast.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/webgl/sigma.webgl.edges.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/webgl/sigma.webgl.edges.fast.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/webgl/sigma.webgl.edges.arrow.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.labels.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.hovers.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.nodes.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.edges.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.edges.curve.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.edges.arrow.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.edges.curvedArrow.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.edgehovers.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.edgehovers.curve.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.edgehovers.arrow.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.edgehovers.curvedArrow.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/canvas/sigma.canvas.extremities.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/svg/sigma.svg.utils.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/svg/sigma.svg.nodes.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/svg/sigma.svg.edges.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/svg/sigma.svg.edges.curve.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/svg/sigma.svg.labels.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/renderers/svg/sigma.svg.hovers.def.js"></script>
    <script type="text/javascript" src="js/sigma/src/middlewares/sigma.middlewares.rescale.js"></script>
    <script type="text/javascript" src="js/sigma/src/middlewares/sigma.middlewares.copy.js"></script>
    <script type="text/javascript" src="js/sigma/src/misc/sigma.misc.animation.js"></script>
    <script type="text/javascript" src="js/sigma/src/misc/sigma.misc.bindEvents.js"></script>
    <script type="text/javascript" src="js/sigma/src/misc/sigma.misc.bindDOMEvents.js"></script>
    <script type="text/javascript" src="js/sigma/src/misc/sigma.misc.drawHovers.js"></script>
    <script type="text/javascript" src="js/sigma/plugins/sigma.parsers.json/sigma.parsers.json.js"></script>
    <script src="js/sigma/plugins/sigma.plugins.animate/sigma.plugins.animate.js"></script>
    <script src="js/sigma/plugins/sigma.layout.noverlap/sigma.layout.noverlap.js"></script>
    <script src="js/sigma/plugins/sigma.parsers.gexf/gexf-parser.js"></script>
    <script src="js/sigma/plugins/sigma.parsers.gexf/sigma.parsers.gexf.js"></script>
    <script src="js/sigma/plugins/sigma.layout.forceAtlas2/worker.js"></script>
    <script src="js/sigma/plugins/sigma.layout.forceAtlas2/supervisor.js"></script>
    <script src="js/sigma/plugins/sigma.plugins.neighborhoods/sigma.plugins.neighborhoods.js"></script>


</head>
<body>
    <#nested>
</body>
</html>
</#macro>