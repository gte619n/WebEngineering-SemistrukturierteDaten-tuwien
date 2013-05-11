/*
 * Copyright 2013 Michi.
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

public class PlayerStorage {
    private static PlayerStorage ps;

    private ArrayList<Player> players = null;

    private PlayerStorage(){
      players = new ArrayList<Player>();
    }

    public static PlayerStorage getInstance(){
        if(ps == null){
            ps = new PlayerStorage();
        }
        return ps;
    }

    public ArrayList<Player> getPlayers() {
      return this.players;
    }

}
