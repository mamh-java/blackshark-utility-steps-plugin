package org.jenkinsci.plugins.blackshark.utility.steps.ini;

import com.google.common.collect.ImmutableSet;
import hudson.Extension;
import hudson.FilePath;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.workflow.steps.Step;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepDescriptor;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import javax.annotation.Nonnull;
import java.util.Set;

public class ReadIniStep extends Step {
    private String file;
    private String section;
    private String option;

    @DataBoundConstructor
    public ReadIniStep(String file, String section, String option) {
        this.file = file;
        this.section = section;
        this.option = option;
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new ReadIniStepExecution(this, context);
    }

    public String getFile() {
        return file;
    }

    @DataBoundSetter
    public void setFile(String file) {
        this.file = file;
    }

    public String getSection() {
        return section;
    }

    @DataBoundSetter
    public void setSection(String section) {
        this.section = section;
    }

    public String getOption() {
        return option;
    }

    @DataBoundSetter
    public void setOption(String option) {
        this.option = option;
    }


    @Extension
    public static class DescriptorImpl extends StepDescriptor {

        public DescriptorImpl() {
        }

        @Override
        public Set<? extends Class<?>> getRequiredContext() {
            return ImmutableSet.of(TaskListener.class, FilePath.class);
        }

        @Override
        public String getFunctionName() {
            return "readIni";
        }


        @Override
        @Nonnull
        public String getDisplayName() {
            return Messages.ReadIniStep_DescriptorImpl_displayName();
        }
    }
}
