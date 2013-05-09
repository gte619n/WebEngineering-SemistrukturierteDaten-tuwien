package formel0api;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name = "lololooolll";

  public String getName() {
    return "lajdsölfkjsaöfdkjaökj";
  }
  public void setName(String name) {
    this.name = name;
  }
}
