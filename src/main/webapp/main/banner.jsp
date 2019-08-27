<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>

<script>
    $(function () {
        $("#bannerList").jqGrid({
            url: "${pageContext.request.contextPath}/banner/getAll",
            editurl: "${pageContext.request.contextPath}/banner/edit",
            datatype: "json",
            colNames: ["id", "标题", "状态", "日期", "描述", "图片"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {
                    name: "status", editable: true, edittype: "select",
                    editoptions: {value: "y:展示;n:不展示"}
                },
                {name: "createDate", formatter: "date"},
                {name: "desc", editable: true},
                {
                    name: "imgPath", editable: true, edittype: "file",
                    formatter: function (cellvalues, options, rowObject) {
                        return "<img style='height:100px;width:150px' src='${pageContext.request.contextPath}/img/" + cellvalues + "'/>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            pager: "#bannerPager",
            height: "60%",
            page: 1,
            rowNum: 2,
            viewrecords: true,
            rowList: [2, 4, 6],
            multiselect: true
        }).jqGrid("navGrid", "#bannerPager",
            {
                search: false
            },

            { //执行编辑时进行的操作
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#title").attr("readonly", true);
                    obj.find("#desc").attr("readonly", true);
                    obj.find("#imgPath").attr("disabled", true);
                }
            },
            {//执行添加时进行额外操作
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseJSON.id;
                    console.log(bannerId);
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        fileElementId: "imgPath",
                        data: {bannerId: bannerId},
                        success: function () {
                            $("#bannerList").trigger("reloadGrid");
                        }
                    });
                    return response;
                }
            },
            {//删除时。。。。

            }
        )
    })
</script>

<table id="bannerList"></table>
<div style="height: 50px" id="bannerPager"></div>