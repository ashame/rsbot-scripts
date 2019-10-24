package net.imashamed.wyvern.tasks;

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

import net.imashamed.api.Task;
import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.Map;

/**
 * @author nathan
 * created on 10/24/2019.
 */

public class Reposition extends Task<ClientContext> {
    private final Area[] SAFESPOTS = {
        new Area(
                new Tile(0, 0, 0),
                new Tile(0, 0, 0)
        )
    };
    public Reposition(ClientContext ctx, Map<String, Object> scriptVariables) {
        super(ctx, scriptVariables);
        setName("Reposition");
    }

    @Override
    public boolean activate() {
        return ctx.players.local().interacting() != ctx.npcs.nil() && !inSafespot();
    }

    @Override
    public void execute() {

    }

    private boolean inSafespot() {
        if (ctx.players.local() != ctx.players.nil()) {
            for (Area a : SAFESPOTS) {
                if (a.contains(ctx.players.local())) {
                    return true;
                }
            }
        }
        return false;
    }
}