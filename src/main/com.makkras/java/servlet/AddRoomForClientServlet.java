package servlet;

import dbHandler.RoomDbService;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addClRoom")
public class AddRoomForClientServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        logger.info(session.getAttribute("name"));
        logger.info(session.getAttribute("password"));
        try {
            if(!RoomDbService.checkIfRoomIsOrdered(Integer.parseInt(req.getParameter("clRoomNumberToAdd")))){
                RoomDbService.addRoomToDb(session.getAttribute("name").toString(),session.getAttribute("password").toString(),
                        Integer.parseInt(req.getParameter("clRoomNumberToAdd")));
            }
            else {
                session.setAttribute("error","This room is already ordered.");
            }
            resp.sendRedirect("/jsp/clientMainMenuForm.jsp");
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
    }
}
