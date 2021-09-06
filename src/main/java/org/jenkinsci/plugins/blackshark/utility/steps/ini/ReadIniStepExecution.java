package org.jenkinsci.plugins.blackshark.utility.steps.ini;

import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;

import javax.annotation.Nonnull;

public class ReadIniStepExecution extends SynchronousNonBlockingStepExecution<String> {
    private static final long serialVersionUID = 1L;

    private transient final ReadIniStep step;

    public ReadIniStepExecution(ReadIniStep step, @Nonnull StepContext context) {
        super(context);
        this.step = step;
    }

    @Override
    protected String run() throws Exception {
        String file = step.getFile();
        String section = step.getSection();
        String option = step.getOption();
        try{
            HierarchicalINIConfiguration ini = new HierarchicalINIConfiguration(file);
            String value = ini.getSection(section).getString(option);
            return value;
        }catch (Exception e){
            return "";
        }

    }

}
