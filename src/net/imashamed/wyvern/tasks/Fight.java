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
import org.powerbot.script.rt4.Npc;

import java.util.Map;

/**
 * @author nathan
 * created on 10/24/2019.
 */

public class Fight extends Task<ClientContext> {
    private final Area[] SAFESPOTS = {
      new Area(
              new Tile(0, 0, 0),
              new Tile(0, 0, 0)
      ),
      new Area(
              new Tile(0, 0, 0),
              new Tile(0, 0, 0)
      )
    };
    private final int[] WYVERN_IDS = {
            1, 2, 3
    };

    public Fight(ClientContext ctx, Map<String, Object> scriptVariables) {
        super(ctx, scriptVariables);
        setName("Fight");
    }

    @Override
    public boolean activate() {
        return ctx.players.local().interacting() == ctx.npcs.nil()
                && ctx.players.local().animation() == -1
                && ctx.players.local().healthPercent() >= 50
                && !ctx.npcs.select().id(WYVERN_IDS).isEmpty();
    }

    @Override
    public void execute() {
        final Npc wyvern = ctx.npcs.nearest().poll();
        if (wyvern.interacting() != ctx.npcs.nil()) {
            if (wyvern.valid() && inSafespot(wyvern)) {
                wyvern.interact("Attack");
            }
        }
    }

    private boolean inSafespot(Npc npc) {
        for (Area a : SAFESPOTS) {
            if (a.contains(npc)) {
                return true;
            }
        }
        return false;
    }
}