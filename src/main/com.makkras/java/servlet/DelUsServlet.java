package servlet;

import dbHandler.UserDbService;
import entity.User;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/delUsAdm")
public class DelUsServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        logger.info(session.getAttribute("name"));
        logger.info(session.getAttribute("password"));
        try {
            if(UserDbService.checkIfUsExists(req.getParameter("usernameToDel"),req.getParameter("delPassword"))){
                UserDbService.delUserFromDb(req.getParameter("usernameToDel"),req.getParameter("delPassword"));
            }else {
                session.setAttribute("error","Such user does not exist.");
            }
            resp.sendRedirect("/jsp/admMainMenuForm.jsp");
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
    }
}
