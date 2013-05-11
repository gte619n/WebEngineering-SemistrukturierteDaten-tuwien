/*
 * Copyright 2013 Matthes Laptop.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package formel0api;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Matthes Laptop
 */

@ManagedBean(name="controller")
@ApplicationScoped
public class RegisterController {
    private ArrayList<Player> registeredPlayer = PlayerStorage.getInstance().getPlayers();

    //@ManagedProperty(value="#{loginPlayer}")
    private Player loginPlayer;

    //@ManagedProperty(value="#{registerPlayer}")
    private Player registerPlayer;

    private boolean loginFailed = false; //Achtung Variable wird falsch herum benutzt!!

    
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();  
    private ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale, Thread.currentThread().getContextClassLoader());
    //private ResourceBundle bundle = ResourceBundle.getBundle("internationalization.I18n", locale, Thread.currentThread().getContextClassLoader());
    

    public RegisterController(){
        super();
        registerPlayer = new Player();
        loginPlayer = new Player();
    }

   public String register() throws ValidatorException{
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

   public String login(){
       if(registeredPlayer.size() > 0){
            for(Player player : registeredPlayer){
                 if(player.getUsername().equals(loginPlayer.getUsername()) && player.getPassword().equals(loginPlayer.getPassword())){
                    return "/table.xhtml";
                 }
            }
       }else{
           loginFailed = true;
           return "/index.xhtml";
       }
        loginFailed = true;
        return "/index.xhtml";
   }

   public void validateFirstname(FacesContext ctx, UIComponent component, Object value) throws ValidatorException{
       String firstname = (String)value;
        if(!firstname.matches("[a-zA-Z]+")){
            
            String str = bundle.getString("firstnameFalse");
            FacesMessage  msg = new FacesMessage(str);
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            
            
            throw new ValidatorException(msg);
     }
   }
   public void validateLastname(FacesContext ctx, UIComponent component, Object value) throws ValidatorException{
       String lastname = (String)value;
       if(!lastname.matches("[a-zA-Z]+")){
            String str = bundle.getString("lastnameFalse");
            FacesMessage  msg = new FacesMessage(str);
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
     }
   }
   public void validateBirthdate(FacesContext ctx, UIComponent component, Object value) throws ValidatorException{
       String birthday = (String)value;
       if(!birthday.matches("^((0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](19|20)[0-9][0-9])?$")){
            String str = bundle.getString("birthdateFalse");
            FacesMessage  msg = new FacesMessage(str);
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
     }
   }

   public void validateSex(FacesContext ctx, UIComponent component, Object value) throws ValidatorException{
       String sex = (String)value;
        if(!sex.matches("[a-zA-Z]+")){
         String str = bundle.getString("sexFalse");
         FacesMessage  msg = new FacesMessage(str);
         msg.setSeverity(FacesMessage.SEVERITY_WARN);
         throw new ValidatorException(msg);
     }
   }

      public void validateUsername(FacesContext ctx, UIComponent component, Object value) throws ValidatorException{
       String username = (String)value;
          if(!username.matches("[a-zA-Z]+")){
            String str = bundle.getString("userFalse");
            FacesMessage  msg = new FacesMessage(str);
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
     }

   }
   public void validatePassword(FacesContext ctx, UIComponent component, Object value) throws ValidatorException{
       String password = (String)value;
       if(!password.matches("^(?=.*\\d)(?=.*[a-zA-Z]).{2,}$")){
            String str = bundle.getString("passwordFalse");
            FacesMessage  msg = new FacesMessage(str);
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
}
