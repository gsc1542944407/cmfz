<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script charset="utf-8" src="kindeditor-all-min.js"></script>
    <script charset="utf-8" src="lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function (K) {
            window.editor = K.create('#editor_id');
        });
    </script>


</head>
<body>
<textarea id="editor_id" name="content" style="width:700px;height:300px;">
&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>
</body>
</html>