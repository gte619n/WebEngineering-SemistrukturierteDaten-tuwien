package formel0api;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean(name="tablecontroller")
@ApplicationScoped
public class TableController{
  private String test = null;

  public String getTest() {
    return Double.toString(Math.random()*10);
  }

  public void setTest(String test) {
    this.test = test;
  }

  public String testAjax() {
    return "test";
  }

}
