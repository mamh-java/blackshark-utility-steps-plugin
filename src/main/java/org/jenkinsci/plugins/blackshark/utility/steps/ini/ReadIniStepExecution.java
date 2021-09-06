package org.jenkinsci.plugins.blackshark.utility.steps.ini;

import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;
import org.ini4j.ConfigParser;

import javax.annotation.Nonnull;
import java.nio.file.Paths;

public class ReadIniStepExecution extends SynchronousNonBlockingStepExecution<String> {
    private static final long serialVersionUID = 1L;

    private transient final ReadIniStep step;

    public ReadIniStepExecution(ReadIniStep step, @Nonnull StepContext context) {
        super(context);
        this.step = step;
    }

    @Override
    protected String run() throws Exception {
        ConfigParser config = new ConfigParser();
        String file = step.getFile();
        String section = step.getSection();
        String option = step.getOption();

        config.read(Paths.get(file).toFile());

        if (!config.hasSection(section))
            return "";

        String value = config.get(section, option);
        return value;
    }
}
