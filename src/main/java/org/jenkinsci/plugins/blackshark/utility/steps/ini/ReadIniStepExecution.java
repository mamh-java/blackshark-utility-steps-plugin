package org.jenkinsci.plugins.blackshark.utility.steps.ini;

import hudson.FilePath;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.jenkinsci.plugins.workflow.steps.MissingContextVariableException;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;

import javax.annotation.Nonnull;
import java.io.InputStream;

import static org.apache.commons.lang.StringUtils.isNotBlank;

public class ReadIniStepExecution extends SynchronousNonBlockingStepExecution<String> {
    private static final long serialVersionUID = 1L;

    private transient final ReadIniStep step;

    public ReadIniStepExecution(ReadIniStep step, @Nonnull StepContext context) {
        super(context);
        this.step = step;
    }

    @Override
    protected String run() throws Exception {
        FilePath ws = getContext().get(FilePath.class);
        if (ws == null && isNotBlank(step.getFile())) {
            throw new MissingContextVariableException(FilePath.class);
        }

        FilePath f = ws.child(step.getFile()); //
        if (f.exists() && !f.isDirectory()) {
            try (InputStream is = f.read()) {
                HierarchicalINIConfiguration ini = new HierarchicalINIConfiguration();
                ini.load(is);
                String section = step.getSection();
                String option = step.getOption();
                String value = "";
                value = ini.getSection(section).getString(option);
                return value;
            }
        }
        return "";


    }

}
