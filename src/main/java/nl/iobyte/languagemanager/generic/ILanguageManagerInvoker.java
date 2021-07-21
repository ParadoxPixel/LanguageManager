package nl.iobyte.languagemanager.generic;

import nl.iobyte.languagemanager.generic.config.interfaces.IConfigurationFactory;
import nl.iobyte.languagemanager.generic.scheduler.IScheduler;

public interface ILanguageManagerInvoker {

    /**
     * Get invokers scheduler implementation
     * @return IScheduler
     */
    IScheduler getScheduler();

    /**
     * Get invokers configuration factory implementation
     * @return IConfigurationFactory
     */
    IConfigurationFactory getFactory();

}
