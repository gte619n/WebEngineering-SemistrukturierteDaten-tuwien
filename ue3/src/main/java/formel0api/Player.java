package formel0api;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ApplicationScoped;

@ManagedBean(name="player")
@SessionScoped
public class Player {
  private List<Integer> history = null;
  private String username = null;
  private int position = -1;
  private String firstname = null;
  private String lastname = null;
  private String birthdate = null;
  private String sex = null;
  private String password = null;
  private int diceResult = -1;

  public Player() {
    super();
    history = new ArrayList<Integer>();
    setPosition(0);
    setDiceResult(0);
  }

  public String getUsername() {
    return this.username;
  }

  public int getPosition() {
    return this.position;
  }

  public void setPosition(int pos) {
    this.position = pos;
    history.add(new Integer(pos));
  }

  public int getPositionMinusT(int t) {
    int index = history.size() - 1 - t;
    if (index >= 0 && index < history.size()) {
      return history.get(index);
    }
    return -1;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public String getSex() {
    return sex;
  }

  public String getPassword() {
    return password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getDiceResult() {
    return this.diceResult;
  }

  public void setDiceResult(int diceResult) {
    this.diceResult = diceResult;
  }

  @Override
  public String toString(){
    return ""+ firstname + " " + lastname + "; " + username + " " + password;
  }

  }
