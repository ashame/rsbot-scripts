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
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;

import java.util.Map;

/**
 * @author nathan
 * created on 10/24/2019.
 */

public class Loot extends Task<ClientContext> {
    final int[] LOOT_IDS = {};

    public Loot(ClientContext ctx, Map<String, Object> scriptVariables) {
        super(ctx, scriptVariables);
        setName("Loot");
    }

    @Override
    public boolean activate() {
        return ctx.players.local().interacting() == ctx.npcs.nil()
                && !ctx.groundItems.select().id(LOOT_IDS).nearest().isEmpty();
    }

    @Override
    public void execute() {
        final GroundItem loot = ctx.groundItems.poll();

    }
}