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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showClData")
public class ShowClientDataServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        logger.info(session.getAttribute("name"));
        logger.info(session.getAttribute("password"));
        try {
            ResultSet resultSet =RoomDbService.showAllDataForAdm();
            List<String> finalResList = new ArrayList<>();
            while (resultSet.next()){
                logger.info(resultSet.getInt(1));
                logger.info(resultSet.getInt(2));
                logger.info(resultSet.getInt(3));

                logger.info(resultSet.getInt(4));
                logger.info(resultSet.getString(5));
                logger.info(resultSet.getString(6));
                logger.info(resultSet.getBoolean(7));
                if(resultSet.getInt(2) == UserDbService.getIdByNameAndPassword(session.getAttribute("name").toString(),session.getAttribute("password").toString())){
                    finalResList.add(resultSet.getString(5)+"           "+resultSet.getString(6)+"           "+resultSet.getBoolean(7) +"           "+ resultSet.getInt(3));
                }
            }
            session.setAttribute("ResultList",finalResList);
            resp.sendRedirect("/jsp/dataListForUs.jsp");
        } catch (CustomException e) {
            logger.error(e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
