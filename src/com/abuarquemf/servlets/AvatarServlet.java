package com.abuarquemf.servlets;

import com.abuarquemf.entities.Avatar;
import com.abuarquemf.entities.Emotion;
import com.abuarquemf.persistence.DataBaseHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@WebServlet(value = "/avatar/*", name = "AvatarServlet", loadOnStartup = 1)
public class AvatarServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -2146985448468604414L;

    private static final String PIC_BASE_DIR = "pic_dir";

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader == null || acceptHeader.contains("application/xml")) {
            // writeJSON(request, response);
            handleJSON(request, response);
        } else
            response.sendError(415);
    }

    private void handleJSON(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        String objectPath = findObjectPathToSend(request);
        String fileToSend = getServletContext().getRealPath("/")
                + PIC_BASE_DIR
                + "/"
                + ((objectPath == null) ? "im_not_found.jpg"
                : (objectPath + ".bmp"));

        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(fileToSend);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }


    private String[] _identifier(HttpServletRequest request) throws Exception {
        String requestUri = request.getRequestURI();
        String[] uriParts = requestUri.split("/");
        String[] toReturn = { "", "" }; // avatar name and emotion
        int index = 0;

        boolean beerContextFound = false;
        for (String part : uriParts) {
            if (part.equals("avatar")) {
                beerContextFound = true;
                continue;
            }
            if (beerContextFound)
                toReturn[index++] = part;
        }
        try {
            toReturn[0] = URLDecoder.decode(toReturn[0], "UTF-8");
        } catch (UnsupportedEncodingException e) {
            toReturn[0] = URLDecoder.decode(toReturn[0]);
        }
        return toReturn;
    }

    private String findObjectPathToSend(HttpServletRequest request) {
        Avatar avatar = null;
        DataBaseHandler persistence = DataBaseHandler.getInstance();
        try {
            String[] identifier = _identifier(request);
            avatar = persistence.findAvatar(identifier[0]);
            if (avatar != null) {
                Emotion em = avatar.findEmotion(identifier[1]);
                if (em == null)
                    return null;
                return avatar.getName() + "_" + em.toString();
            } else
                return null;

        } catch (Exception e) {
            return null;
        }

    }
}
