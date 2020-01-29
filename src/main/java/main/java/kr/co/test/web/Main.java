package main.java.kr.co.test.web;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;

// 톰캣서버가동
public class Main {
    public static void main(String[] args) throws LifecycleException, ServletException {
        String webappDirLocation = "src/main/webapp";
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");

        if(webPort == null || webPort.isEmpty())
        {
            webPort = "8000";
        }

        tomcat.setPort(Integer.parseInt(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/",
                new File(webappDirLocation).getAbsolutePath());
        System.out.println(new File("./" + webappDirLocation).getAbsolutePath());

        File additionWebInfClass = new File("target/classes");

        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClass.getAbsolutePath(), "/"));

        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();

    }
}
