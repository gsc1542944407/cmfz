<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="../echarts/echarts.min.js"></script>
    <script src="../echarts/china.js"></script>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
</head>

<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    option = {
        title: {
            text: '注册用户',
            subtext: '纯属虚构',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['user']
        },
        visualMap: {
            min: 0,
            max: 200,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: 'user',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                }
            }

        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    var goEasy = new GoEasy({
        appkey: "BC-f4868785bc6e4b3d91186bf55e673660"
    });
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            var da = JSON.parse(message.content);
            console.log(da);
            myChart.setOption({
                series: [{
                    data: da
                }]
            })
        }
    });

    function btn() {
        $.ajax({
            url: "${pageContext.request.contextPath}/user/user",

        });


    }
</script>
<button onclick="btn()">查看</button>
</body>

</html>