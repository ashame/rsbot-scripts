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
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Magic;

import java.util.Map;

/**
 * @author nathan
 * created on 10/24/2019.
 */

public class Alch extends Task<ClientContext> {
    final int ALCH_ANIMATION = 0;
    final int[] ALCH_IDS = {1};

    public Alch(ClientContext ctx, Map<String, Object> scriptVariables) {
        super(ctx, scriptVariables);
        setName("Alch");
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation() != ALCH_ANIMATION;
    }

    @Override
    public void execute() {
        final Item toAlch = ctx.inventory.select().id(ALCH_IDS).poll();
        if (toAlch != null) {
            if (ctx.magic.ready(Magic.Spell.HIGH_ALCHEMY)) {
                if (ctx.magic.cast(Magic.Spell.HIGH_ALCHEMY)) {
                    if (toAlch.click()) {
                        scriptVariables.replace("currentTask", "Waiting");
                    }
                }
            }
        }
    }
}
