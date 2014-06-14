package com.forgeessentials.util.events.modules;

import com.forgeessentials.core.moduleLauncher.CallableMap;
import com.forgeessentials.core.moduleLauncher.ModuleContainer;
import com.forgeessentials.util.OutputHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLStateEvent;

import java.util.logging.Logger;

public class FEModulePreInitEvent extends FEModuleEvent {
    private FMLPreInitializationEvent event;
    private CallableMap callables;

    public FEModulePreInitEvent(ModuleContainer container, FMLPreInitializationEvent event, CallableMap map)
    {
        super(container);
        this.event = event;
        callables = map;
    }

    /**
     * Get a logger instance configured to write to the FE Log as a parent,
     * identified by ModuleName. Handy for module logging!
     *
     * @return A logger
     */
    public Logger getModLog()
    {
        Logger log = Logger.getLogger(container.name);
        log.setParent(OutputHandler.felog);
        return log;
    }

    @Override
    public FMLStateEvent getFMLEvent()
    {
        return event;
    }

    public CallableMap getCallableMap()
    {
        return callables;
    }
}
