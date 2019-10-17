package contollers.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ServletService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UploadFileServlet", value = "/upload")
public class UploadFileServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(UserService.class);

    @Override
    public void init() throws ServletException {

        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(req)) {
            String path = "";
            try {
                List<FileItem> multiParts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
                for (FileItem item : multiParts) {
                    if (!item.isFormField()) {

                        String name = new File(item.getName()).getName();
                        String UPLOAD_DIRECTORY = "C:/uploads";
                        path = UPLOAD_DIRECTORY + File.separator + name;
                        item.write(new File(path));
                        req.setAttribute("path",path);
                        req.setAttribute("message", ServletService.UPLOAD_CORRECT);
                    }
                }
            } catch (Exception e) {
                logger.error("FAILED TO LOAD FILE");
                e.printStackTrace();
                req.setAttribute("message", "File Upload Failed due to " + e);
            }
            req.getRequestDispatcher("parse").forward(req, resp);
        }
    }
}
