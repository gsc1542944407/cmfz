<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>

<script>
    $(function () {
        $("#albumList").jqGrid({
            url: "${pageContext.request.contextPath}/album/getAll",
            editurl: "${pageContext.request.contextPath}/album/edit",
            datatype: "json",
            colNames: ["id", "标题", "分数", "作者", "播音", "集数", "简介", "发布时间", "状态", "封面"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "score"},
                {name: "author", editable: true},
                {name: "broadcast", editable: true},
                {name: "count"},
                {name: "brief", editable: true},
                {name: "publishDate", formatter: "date"},
                {
                    name: "status", editable: true, edittype: "select",
                    editoptions: {value: "y:展示;n:不展示"}
                },
                {
                    name: "cover", editable: true, edittype: "file",
                    formatter: function (cellValue, options, rowObject) {
                        return "<img style='height:80px;width:100px' src='${pageContext.request.contextPath}/img/" + cellValue + "'/>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            pager: "#albumPager",
            height: "60%",
            page: 1,
            rowNum: 2,
            viewrecords: true,
            rowList: [2, 4, 6],
            multiselect: true,
            subGrid: true,
            subGridRowExpanded: function (subGridId, albumId) {
                addSubGrid(subGridId, albumId);
            }
        }).jqGrid("navGrid", "#albumPager", {search: false, del: false},
            {
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#cover").attr("disabled", true);
                }
            },
            {
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var albumId = response.responseJSON.id;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/album/upload",
                        fileElementId: "cover",
                        data: {albumId: albumId},
                        success: function () {
                            $("#albumList").trigger("reloadGrid");
                        }
                    });

                    return response;
                }
            }
        )
    });

    function addSubGrid(subGridId, albumId) {
        var tableId = subGridId + "table";
        var pagerId = subGridId + "pager";
        $("#" + subGridId).html(
            "<table id='" + tableId + "' ></table>" +
            "<div id='" + pagerId + "'></div>"
        );
        $("#" + tableId).jqGrid({
            url: "${pageContext.request.contextPath}/chapter/getAll?albumId=" + albumId,
            editurl: "${pageContext.request.contextPath}/chapter/edit?albumId=" + albumId,
            datatype: "json",
            colNames: ["id", "标题", "音频", "大小", "时长", "状态", "创建时间", "albumId", "操作"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "url", editable: true, edittype: "file"},
                {name: "size"},
                {name: "duration"},
                {
                    name: "status", editable: true,
                    edittype: "select",
                    editoptions: {value: "y:展示;n:不展示"}
                },
                {name: "createDate", formatter: "date"},
                {name: "albumId"},
                {
                    name: "url",
                    formatter: function (a, b, c) {
                        return "<a onclick=\"playAudio('" + a + "')\" href='#'> <span class='glyphicon glyphicon-play'></span></a>" + "          " +
                            "<a onclick=\"downloadAudio('" + a + "')\" href='#'> <span class='glyphicon glyphicon-circle-arrow-down'></span></a>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            pager: "#" + pagerId,
            height: "60%",
            page: 1,
            rowNum: 2,
            viewrecords: true,
            rowList: [2, 4, 6],
            multiselect: true
        }).jqGrid("navGrid", "#" + pagerId, {search: false, del: false, edit: false},
            {},
            {
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var chapterId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/chapter/upload",
                        fileElementId: "url",
                        data: {chapterId: chapterId},
                        success: function () {
                            $("#" + tableId).trigger("reloadGrid");
                        }
                    })
                }
            }
        )

    }

    function playAudio(a) {
        $("#audioModel").modal("show");
        $("#audioId").attr("src", "${pageContext.request.contextPath}/audio/" + a);
    }

    function downloadAudio(a) {
        location.href = "${pageContext.request.contextPath}/chapter/down?audioName=" + a;
    }

</script>


<div id="audioModel" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audioId" src="" controls></audio>
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<table id="albumList"></table>
<div style="height: 50px" id="albumPager"></div>