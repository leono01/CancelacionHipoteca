/*
 * Copyright (C) 2015 GISNET
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gisnet.cancelacion.events;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marco-g8
 */
public class MultipleParams {
    
    private final Map<String, Object> params;

    public MultipleParams() {
        this.params = new HashMap<>();
    }
    
    public void add(String key, Object value) {
        this.params.put(key, value);
    }
    
    public Object get(String key) {
        return this.params.get(key);
    }
    
    public boolean containsKey(String key) {
        return this.params.containsKey(key);
    }
    
}
