package fr.xebia.katas.gildedrose;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import java.nio.ByteBuffer;

import static org.eclipse.jetty.http.HttpHeader.CONTENT_TYPE;
import static org.eclipse.jetty.http.HttpStatus.OK_200;

public class Server {

    final Inn inn;

    Server() {
        inn = new Inn();
    }

    public String handle() {
        StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("<body><div id=\"posts\"><h1>Gilded Rose</h1><p>");
        out.append("<table><tr><th>Name</th><th>Sell in</th><th>Quality</th></tr>");
        for (Item item : inn.getItems()) {
            out.append("<tr>");
            out.append("<td>").append(item.getName()).append("</td>");
            out.append("<td>").append(item.getSellIn()).append("</td>");
            out.append("<td>").append(item.getQuality()).append("</td>");
            out.append("</tr>");
        }
        out.append("</table></p>");
        out.append("<form method=\"post\">");
        out.append("<input type=\"submit\" value=\"increaseQuality\">");
        out.append("</form>");
        out.append("</div></body></html>");
        return out.toString();
    }

    public static void main(String[] args) throws Throwable {
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(8080);
        final Server myServer = new Server();

        server.setHandler(new Handler.Abstract() {
            @Override
            public boolean handle(Request request, Response response, Callback callback) {
                response.setStatus(OK_200);
                response.getHeaders().put(CONTENT_TYPE, "text/html;charset=utf-8");
                if ("POST".equals(request.getMethod())) {
                    myServer.inn.updateQuality();
                }
                response.write(true, ByteBuffer.wrap(myServer.handle().getBytes()), callback);
                callback.succeeded();
                return true;
            }
        });

        server.setStopAtShutdown(true);
        server.start();
        server.join();
    }

}
