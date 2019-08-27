<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
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
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '持明法州用户注册统计'
        },
        tooltip: {},
        legend: {
            data: ['注册量']
        },
        xAxis: {
            data: ["第一天", "第二天", "第三天", "第四天", "第五天", "第六天", "第七天"]
        },
        yAxis: {},
        series: [{
            name: '注册量',
            type: 'line',
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    //--------------------------------------
    var goEasy = new GoEasy({
        appkey: "BC-f4868785bc6e4b3d91186bf55e673660"
    });
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {

            var da = JSON.parse(message.content);
            myChart.setOption({
                series: [{
                    data: da
                }],
            })

        }
    });

    function btn() {
        $.ajax({
            url: "${pageContext.request.contextPath}/user/count",
        });

    }
</script>
</body>

<button onclick="btn()">查看</button>

</html>