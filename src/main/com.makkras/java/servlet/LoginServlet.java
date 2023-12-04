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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean isAdmin =false;
        HttpSession session = req.getSession();
        if(req.getParameter("register")==null){
            if(req.getParameter("enterAsAdmin")!=null){
                isAdmin=true;
            }
            User user = new User(req.getParameter("username"),req.getParameter("password"),isAdmin);
            logger.info("USERNAME : " + user.getName() + " PASSWORD : "+user.getPassword());
            logger.info("AS ADMIN?: "+ isAdmin);
            try {
                if(UserDbService.checkIfUsExists(user.getName(), user.getPassword())){
                    if(user.isAdmin()){
                        if(UserDbService.checkIfUsIsAdm(user.getName(), user.getPassword(),user.isAdmin())){
                            session.setAttribute("name",user.getName());
                            session.setAttribute("password",user.getPassword());
                            resp.sendRedirect("/jsp/admMainMenuForm.jsp");
                        }else {
                            session.setAttribute("error","Such admin doesn't exist.");
                            resp.sendRedirect("/jsp/loginMenu.jsp");
                        }
                    }else {
                        session.setAttribute("name",user.getName());
                        session.setAttribute("password",user.getPassword());
                        resp.sendRedirect("/jsp/clientMainMenuForm.jsp");
                    }
                }else {
                    session.setAttribute("error","Such user doesn't exist.");
                    resp.sendRedirect("/jsp/loginMenu.jsp");
                }
            } catch (CustomException e) {
                logger.error(e.getMessage());
            }
        }else {
            User user = new User(req.getParameter("username"),req.getParameter("password"),false);
            logger.info("NEW USERNAME : " + user.getName() + "NEW PASSWORD : "+user.getPassword());
            try {
                UserDbService.addUsToDb(user.getName(), user.getPassword(), user.isAdmin());
                session.setAttribute("name",user.getName());
                session.setAttribute("password",user.getPassword());
                resp.sendRedirect("/jsp/clientMainMenuForm.jsp");
            } catch (CustomException e) {
                logger.error(e.getMessage());
            }

        }

    }
}
