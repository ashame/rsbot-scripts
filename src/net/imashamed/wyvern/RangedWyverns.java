package net.imashamed.wyvern;

/*
 *  This file is part of rsbot.
 *
 *  rsbot-scripts is free software: you can redistribute it and/or modify
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
import net.imashamed.wyvern.tasks.*;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author nathan
 * created on 10/24/2019.
 */

@Script.Manifest(
        name = "Ranged Wyverns",
        description = "safe-spot ranges skeletal wyverns",
        properties = "client=4")

public class RangedWyverns extends PollingScript<ClientContext> {
    private final int[] ALCHABLES = {1, 2, 3};
    private final int[] WYVERN_IDS = {1, 2, 3};

    private List<Task> taskList = new ArrayList<>();
    private Map<String, Object> scriptVars = new ConcurrentHashMap<>();

    @Override
    public void start() {
        taskList.addAll(Arrays.asList(
                new Alch(ctx, scriptVars),
                new Fight(ctx, scriptVars),
                new Loot(ctx, scriptVars),
                new Reposition(ctx, scriptVars)
        ));
        scriptVars.put("alchableItems", ALCHABLES);
        scriptVars.put("wyvernIds", WYVERN_IDS);
        scriptVars.put("currentTask", "None");
    }

    @Override
    public void poll() {
        for (Task task : taskList) {
            if (task.activate()) {
                scriptVars.put("currentTask", task.getName());
                try {
                    task.execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    scriptVars.put("currentTask", "ERROR");
                }
            }
        }
    }
}