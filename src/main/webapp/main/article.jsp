<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>


<script>
    $(function () {
        $("#articleList").jqGrid({
            url: "${pageContext.request.contextPath}/article/getAll",
            datatype: "json",
            colNames: ["id", "标题", "作者", "内容", "创建时间", "状态", "操作"],
            colModel: [
                {name: "id"},
                {name: "title"},
                {name: "author"},
                {name: "content", hidden: true},
                {name: "publishDate", formatter: "date"},
                {name: "status"},
                {
                    name: "",
                    formatter: function (a, b, c) {
                        return "<a onclick=\"lookModel('" + c.id + "')\" href='#'>查看详情</a>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            pager: "#articlePager",
            height: "60%",
            page: 1,
            rowNum: 2,
            viewrecords: true,
            rowList: [2, 4, 6],
            multiselect: true
        })
    });

    function showModel() {
        $("#myModal").modal("show");
        $("#addArticleFrom")[0].reset();
        $("#articleId").val("");
        $("#modal_footer").html(
            "<button type=\"button\" onclick=\"addArticle()\" class=\"btn btn-primary\">添加</button>\n" +
            "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>"
        );

        KindEditor.create('#editor', {
            uploadJson: "${pageContext.request.contextPath}/kindeditor/upload",
            filePostName: "img",
            allowFileManager: true,
            fileManagerJson: "${pageContext.request.contextPath}/kindeditor/allImages",
            afterBlur: function () {
                this.sync();
            }
        });
        KindEditor.html("#editor", "");
    }

    function addArticle() {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/add",
            datatype: "json",
            type: "post",
            data: $("#addArticleFrom").serialize(),
            success: function (data) {
                $("#myModal").modal("toggle");
            }
        })
    }

    function lookModel(id) {
        $("#myModal").modal("show");
        var value = $("#articleList").jqGrid("getRowData", id);
        $("#title").val(value.title);
        $("#author").val(value.author);
        $("#status").val(value.status);
        $("#articleId").val(value.id);

        $("#modal_footer").html(
            "<button type=\"button\" onclick=\"updateArticle()\" class=\"btn btn-primary\">修改</button>\n" +
            "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>"
        );
        KindEditor.create('#editor', {
            uploadJson: "${pageContext.request.contextPath}/kindeditor/upload",
            filePostName: "img",
            allowFileManager: true,
            fileManagerJson: "${pageContext.request.contextPath}/kindeditor/allImages",
            afterBlur: function () {
                this.sync();
            }
        });
        KindEditor.appendHtml("#editor", value.content);
    }

    function updateArticle() {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/update",
            datatype: "json",
            type: "post",
            data: $("#addArticleFrom").serialize(),
            success: function (data) {
                $("#myModal").modal("toggle");
            }
        })
    }


</script>

<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">文章列表</a></li>
        <li role="presentation"><a href="#profile" onclick="showModel()" aria-controls="profile" role="tab"
                                   data-toggle="tab">添加文章</a></li>
    </ul>


</div>
<table id="articleList"></table>
<div style="height: 50px" id="articlePager"></div>

<div class="modal fade" id="myModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px">
            <!--模态框标题-->
            <div class="modal-header">
                <!--
                    用来关闭模态框的属性:data-dismiss="modal"
                -->
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title">编辑用户信息</h4>
            </div>

            <!--模态框内容体-->
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/article/editArticle" class="form-horizontal"
                      id="addArticleFrom">
                    <div class="form-group">
                        <div class="col-sm-5">
                            <input type="hidden" name="id" id="articleId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">标题</label>
                        <div class="col-sm-5">
                            <input type="text" name="title" id="title" placeholder="请输入标题" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">作者</label>
                        <div class="col-sm-5">
                            <input type="text" name="author" id="author" placeholder="请输入标题" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="status" id="status">
                                <option value="y">展示</option>
                                <option value="n">不展示</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <textarea id="editor" name="content" style="width:700px;height:300px;"></textarea>
                        </div>
                    </div>
                    <input id="addInsertImg" name="insertImg" hidden>
                </form>
            </div>
            <!--模态页脚-->
            <div class="modal-footer" id="modal_footer">
                <%--<button type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>--%>
            </div>
        </div>
    </div>
</div>