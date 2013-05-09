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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Matthes Laptop
 */

@ManagedBean(name="controller")
@ApplicationScoped
public class RegisterController {
    private ArrayList<Player> registeredPlayer = new ArrayList<Player>();

    //@ManagedProperty(value="#{loginPlayer}")
    private Player loginPlayer;

    //@ManagedProperty(value="#{registerPlayer}")
    private Player registerPlayer;

    private boolean loginFailed = false;

    public RegisterController(){
        super();
        registerPlayer = new Player();
        loginPlayer = new Player();
        
        
    }

   public String register(){
 
   //validation
     if(!registerPlayer.getFirstname().matches("[a-zA-Z]+")){
         FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Invalid Firstname!", null);
            throw new ValidatorException(msg);
     }
     if(!registerPlayer.getLastname().matches("[a-zA-Z]+")){
             FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Invalid Lastname!", null);
            throw new ValidatorException(msg);
     }
     if(!registerPlayer.getBirthdate().matches("^((0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](19|20)[0-9][0-9])?$")){
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Invalid Birthdate!", null);
            throw new ValidatorException(msg);
     }
     if(!registerPlayer.getSex().matches("[a-zA-Z]+")){
             FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Invalid Sex!", null);
            throw new ValidatorException(msg);
     }
     if(!registerPlayer.getUsername().matches("[a-zA-Z]+")){
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Invalid Username!", null);
            throw new ValidatorException(msg);
     }
     if(!registerPlayer.getPassword().matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{2,})")){
             FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Invalid Password!", null);
            throw new ValidatorException(msg);
     }
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

     return "/login";
     
   }

   public String login(){
       if(registeredPlayer.size() > 0){  
            for(Player player : registeredPlayer){
                 if(!(player.getUsername().equals(loginPlayer.getUsername()) && player.getPassword().equals(loginPlayer.getPassword()))){
                    loginFailed = true;
                    return "/login.xhtml";
                 }
            }
       }else{
           return "/login.xhtml";
            
       }
        return "/table.html";
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
