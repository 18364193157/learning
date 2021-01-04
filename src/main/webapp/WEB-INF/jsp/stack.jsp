<%--
  Created by IntelliJ IDEA.
  User: langyonghe
  Date: 2020/12/30
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map"%>
<html>
<head>
    <title>服务器线程信息</title>
</head>
<body>
<pre>
    <%
        for (Map.Entry<Thread,StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()){
            Thread thread = stackTrace.getKey();
            StackTraceElement[] stack = stackTrace.getValue();
            if(thread.equals(Thread.currentThread())){
                continue;
            }
            System.out.println("\n线程：" + thread.getName() + "\n");
            for(StackTraceElement element : stack){
                System.out.println("\t" + element + "\n");
            }
        }
    %>
</pre>

</body>
</html>
