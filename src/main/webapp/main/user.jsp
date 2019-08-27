<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>

<script>
    $(function () {
        $("#userList").jqGrid({
            url: "${pageContext.request.contextPath}/user/getAll",

            datatype: "json",
            colNames: ["id", "手机号", "状态", "密码", "名字", "法号", "性别", "省", "市", "签名", "注册日期", "图片"],
            colModel: [
                {name: "id"},
                {name: "phone", editable: true},
                {
                    name: "status", editable: true, edittype: "select",
                    editoptions: {value: "y:展示;n:不展示"}
                },
                {name: "password", editable: true},
                {name: "name", editable: true},
                {name: "dharma", editable: true},
                {
                    name: "sex", editable: true, edittype: "select",
                    editoptions: {value: "1:男;2:女"}
                },
                {name: "province", editable: true},
                {name: "city", editable: true},
                {name: "sign", editable: true},
                {name: "createDate", formatter: "date"},
                {
                    name: "headPic", editable: true, edittype: "file",
                    formatter: function (cellvalues, options, rowObject) {
                        return "<img style='height:100px;width:150px' src='${pageContext.request.contextPath}/img/" + cellvalues + "'/>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            pager: "#userPager",
            height: "60%",
            page: 1,
            rowNum: 2,
            viewrecords: true,
            rowList: [2, 4, 6],
            multiselect: true
        })
    })

    function outUser() {
        location.href = "${pageContext.request.contextPath}/user/out"
    }
</script>
<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">用户列表</a></li>
        <li role="presentation"><a href="#profile" onclick="outUser()" aria-controls="profile" role="tab"
                                   data-toggle="tab">导出用户</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/main/echarts.jsp">用户注册地</a></li>
        <li role="presentation"><a href="${pageContext.request.contextPath}/main/customer-line.jsp">七天注册人数</a></li>


    </ul>


</div>

<table id="userList"></table>
<div style="height: 50px" id="userPager"></div>