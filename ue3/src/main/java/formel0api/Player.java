/**
 * <copyright>
 *
 * Copyright (c) 2010 http://www.big.tuwien.ac.at All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */
package formel0api;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ApplicationScoped;

/**
 * Class representing a player playing in a {@link Game}.
 */


@ManagedBean(name="player")
@ApplicationScoped
public class Player {

    /**
     * History of the positions of the player
     */
    private List<Integer> history = new ArrayList<Integer>();
    /**
     * The name of this user
     */
    private String username;
    /**
     * The current position of the user's car
     */
    private int position = 0;

    private String firstname;

    private String lastname;

    private String birthdate;

    private String sex;

    private String password;

     /**
     * Initializes a {@link Player} with the specified
     * <code>name</code>.
     *
     * @param name to set
     */
    public Player() {
        super();
        setPosition(0);
    }

    /**
     * Returns the name of this player.
     *
     * @return the name
     */
    public String getUsername() {
        return "loool";
    }

    /**
     * Return the actual position of this player's car
     *
     * @return the actual position of this player's car
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Sets the actual position of this player's car
     *
     * @param pos actual position of this player's car
     */
    public void setPosition(int pos) {
        this.position = pos;
        history.add(new Integer(pos));
    }

    /**
     * Returns the position at time (now - t) (i.e., if t=0 returns the current
     * position, if t=1 returns last position, etc.)
     *
     * @param t position at time (now - t)
     */
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

}
