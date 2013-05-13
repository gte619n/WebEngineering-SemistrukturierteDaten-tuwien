package formel0api;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "controller")
@SessionScoped
public class Controller {
    // Player Storage
    private ArrayList<Player> registeredPlayer = null;
    // register Data
    private Player registerPlayer = null;
    private boolean showTos = false;
    // login Data
    private Player loginPlayer = null;
    private boolean loginFailed = false;
    private Player computerPlayer = null;
    private Game game = null;
    // locale Data
    private Locale locale = null;
    private ResourceBundle bundle = null;

    public Controller() {
        super();
        registeredPlayer = PlayerStorage.getInstance().getPlayers();
        registerPlayer = new Player();
        showTos = false;
        loginPlayer = new Player();
        computerPlayer = PlayerStorage.getInstance().getComputerPlayer();
        loginFailed = false;
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        bundle = ResourceBundle.getBundle("i18n", locale, Thread.currentThread().getContextClassLoader());
    }

    public String register() throws ValidatorException {
        //Wenn Validierung erfolgreich...
        //Player erzeugen und Attribute setzen.
        Player player = new Player();
        player.setFirstname(registerPlayer.getFirstname());
        player.setLastname(registerPlayer.getLastname());
        player.setBirthdate(registerPlayer.getBirthdate());
        player.setSex(registerPlayer.getSex());
        player.setUsername(registerPlayer.getUsername());
        player.setPassword(registerPlayer.getPassword());

        //Zur ArrayList
        registeredPlayer.add(player);

        return "/index.xhtml";
    }

    public String login() {
        if (registeredPlayer.size() > 0) {
            for (Player player : registeredPlayer) {
                if (player.getUsername().equals(loginPlayer.getUsername()) && player.getPassword().equals(loginPlayer.getPassword())) {
                    game = new Game(player, computerPlayer);
                    return "/table.xhtml";
                }
            }
        } else {
            loginFailed = true;
            return "/index.xhtml";
        }
        loginFailed = true;
        return "/index.xhtml";
    }

    public String newGame() {
        Player fu = game.getPlayer();
        fu.setPosition(0);

        Player bar = game.getComputer();
        bar.setPosition(0);
        this.game = new Game(fu, bar);
        return "/table.xhtml";
     }

     public void performDice() {
        game.getPlayer().setDiceResult(game.rollthedice(game.getPlayer()));
        if (game.isGameOver())
            return;
        game.getComputer().setDiceResult(game.rollthedice(game.getComputer()));
     }




    public void validateFirstname(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String firstname = (String) value;
        if (!firstname.matches("[a-zA-Z]+")) {

            String str = bundle.getString("firstnameFalse");
            FacesMessage msg = new FacesMessage(str);
            msg.setSeverity(FacesMessage.SEVERITY_WARN);


            throw new ValidatorException(msg);
        }
    }

    public void validateLastname(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String lastname = (String) value;
        if (!lastname.matches("[a-zA-Z]+")) {
            String str = bundle.getString("lastnameFalse");
            FacesMessage msg = new FacesMessage(str);
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }
    }

    public void validateBirthdate(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String birthday = (String) value;
        if (!birthday.matches("^((0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](19|20)[0-9][0-9])?$")) {
            String str = bundle.getString("birthdateFalse");
            FacesMessage msg = new FacesMessage(str);
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }
    }

    public void validateSex(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String sex = (String) value;
        if (!sex.matches("[a-zA-Z]+")) {
            String str = bundle.getString("sexFalse");
            FacesMessage msg = new FacesMessage(str);
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }
    }

    public void validateUsername(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String username = (String) value;
        if (!username.matches("[a-zA-Z]+")) {
            String str = bundle.getString("userFalse");
            FacesMessage msg = new FacesMessage(str);
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }

    }

    public void validatePassword(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String password = (String) value;
        if (!password.matches("^(?=.*\\d)(?=.*[a-zA-Z]).{2,}$")) {
            String str = bundle.getString("passwordFalse");
            FacesMessage msg = new FacesMessage(str);
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }
    }

    public Player getLoginPlayer() {
        return loginPlayer;
    }

    public void setLoginPlayer(Player loginPlayer) {
        this.loginPlayer = loginPlayer;
    }

    public Player getRegisterPlayer() {
        return registerPlayer;
    }

    public void setRegisterPlayer(Player registerPlayer) {
        this.registerPlayer = registerPlayer;
    }

    public boolean isLoginFailed() {
        return loginFailed;
    }

    public void setLoginFailed(boolean loginFailed) {
        this.loginFailed = loginFailed;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean getShowTos() {
      return this.showTos;
    }

    public void setShowTos(boolean showTos) {
      this.showTos = showTos;
    }
}
