package servlet;

import dbHandler.RoomDbService;
import dbHandler.UserDbService;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/delClRoom")
public class DelClientRoomServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        logger.info(session.getAttribute("name"));
        logger.info(session.getAttribute("password"));
        try {
            int id1 =UserDbService.getIdByNameAndPassword(session.getAttribute("name").toString(),session.getAttribute("password").toString());
            logger.info(id1);
            int id2 = RoomDbService.getIdByNumber(Integer.parseInt(req.getParameter("clRoomNumberToDel")));
            logger.info(id2);
            if(RoomDbService.checkIfRoomIsOrdered(Integer.parseInt(req.getParameter("clRoomNumberToDel"))) && id1==id2){
                RoomDbService.delAnyRoomFromDb(Integer.parseInt(req.getParameter("clRoomNumberToDel")));
            }
            else {
                session.setAttribute("error","This room is not ordered.");
            }
            resp.sendRedirect("/jsp/clientMainMenuForm.jsp");
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
    }
}
