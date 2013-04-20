import Beans.Player;
import Beans.Game;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonFactory;

public class TableServlet extends HttpServlet {
  private Player player1 = null;
  private Player player2 = null;
  private Game game = null;
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession(true);
    session.setAttribute("player1", player1);
    session.setAttribute("player2", player2);
    session.setAttribute("game", game);
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/table.jsp");
    dispatcher.forward(request, response);

  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    player1 = new Player("Huber");
    player2 = new Player("Super C");
    game = new Game(player1, player2);
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // prepare request
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    // set up JSON writer
    JsonFactory jsonFactory = new JsonFactory();
    JsonGenerator jsonResponse = jsonFactory.createJsonGenerator(response.getWriter());
    jsonResponse.writeStartObject();

    //JSON Body
    jsonResponse.writeStringField("gameTime", game.getTime());

    // end JSON writer
    jsonResponse.writeEndObject();
    jsonResponse.close();

  }

  @Override
  public String getServletInfo() {
    return "Handles Table Requests";
    }
  }
