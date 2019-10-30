package net.imashamed.framework;

/*
 *  This file is part of rsbot.
 *
 *  rsbot is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  rsbot is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with rsbot.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.ClientContext;

import java.util.Map;

/**
 * @author nathan
 * created on 10/24/2019.
 */

public abstract class Task<C extends ClientContext> extends ClientAccessor<C> {
    public Map<String, Object> scriptVariables;
    public String name;

    public Task(C ctx) {
        this(ctx, null, "");
    }

    public Task (C ctx, String name) {
        this(ctx, null, name);
    }

    public Task (C ctx, Map<String, Object> scriptVariables) {
        this(ctx, scriptVariables, "");
    }

    public Task (C ctx, Map<String, Object> scriptVariables, String name) {
        super(ctx);
        this.scriptVariables = scriptVariables;
        this.name = name;
    }

    public abstract boolean activate();
    public abstract void execute();

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
