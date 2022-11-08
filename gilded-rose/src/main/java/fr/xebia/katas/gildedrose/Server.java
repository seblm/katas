package fr.xebia.katas.gildedrose;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.handler.AbstractHandler;

public class Server {

    final Inn inn;

    Server() {
        inn = new Inn();
    }

    public String handle() {
        StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("<link rel=\"stylesheet\" href=\"http://www.code-story.net/css/screen.css\" type=\"text/css\" media=\"screen, projection\" />");
        out.append("<body><div id=\"posts\"><h1>Guilded Rose</h1><img class=\"logo\" src=\"http://www.pamsclipart.com/clipart_images/red_rose_bloom_design_0515-1001-2620-0510_SMU.jpg\"><p>");
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
        org.mortbay.jetty.Server server = new org.mortbay.jetty.Server(8080);
        final Server myServer = new Server();

        server.addHandler(new AbstractHandler() {
            @Override
            public void handle(String arg0, HttpServletRequest arg1, HttpServletResponse response, int arg3)
                    throws IOException, ServletException {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                if ("POST".equals(arg1.getMethod())) {
                    myServer.inn.updateQuality();
                }
                response.getWriter().println(myServer.handle());
                response.getWriter().flush();
            }
        });

        server.start();
        server.join();
    }

}
