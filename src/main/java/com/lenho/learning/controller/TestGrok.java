package com.lenho.learning.controller;

import io.krakens.grok.api.GrokCompiler;

import java.util.Map;

/**
 * @author langyonghe
 * @date 2021/5/6 9:45
 */
public class TestGrok {
    public static void main(String[] args) throws Exception {
        useJavaGrok();
    }




    public static void useJavaGrok() throws Exception {
        GrokCompiler grokCompiler = GrokCompiler.newInstance();
        grokCompiler.registerDefaultPatterns();
        final io.krakens.grok.api.Grok grok = grokCompiler.compile("%{COMBINEDAPACHELOG}");
        String log = "112.169.19.192 - - [06/Mar/2013:01:36:30 +0900] \"GET / HTTP/1.1\" 200 44346 \"-\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.152 Safari/537.22\"";
        String log2 = "2021-04-27 14:41:31,895 ERROR [Timer-Driven Process Thread-36] o.a.n.c.s.StandardControllerServiceNode DBCPConnectionPool[id=3c6a873f-016f-1000-f99f-dc3b020dc486] Failed to invoke";
        io.krakens.grok.api.Match grokMatch = grok.match(log2);
        final Map<String, Object> capture = grokMatch.capture();
        System.out.println(capture);
    }
}
