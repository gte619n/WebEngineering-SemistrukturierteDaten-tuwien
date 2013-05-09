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
import java.util.Date;
import java.util.List;

/**
 * Class representing a player playing in a {@link Game}.
 */
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
    public Player(String firstname, String lastname, String birthdate, String sex, String username, String password) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.sex = sex;
        this.username = username;
        this.password = password;
                
        setPosition(0);
    }

    /**
     * Returns the name of this player.
     *
     * @return the name
     */
    public String getUsername() {
        return username;
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
    
    
}
