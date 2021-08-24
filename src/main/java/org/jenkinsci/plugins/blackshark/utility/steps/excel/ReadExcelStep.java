package org.jenkinsci.plugins.blackshark.utility.steps.excel;

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


public class ReadExcelStep extends Step {

    private String file;

    @DataBoundConstructor
    public ReadExcelStep(String file) {
        this.file = file;
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new ReadExcelStepExecution(this, context);
    }

    public String getFile() {
        return this.file;
    }

    @DataBoundSetter
    public void setFile(String file) {
        this.file = file;
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
            return "readExcel";
        }

        @Override
        @Nonnull
        public String getDisplayName() {
            return Messages.ReadExcelStep_DescriptorImpl_displayName();
        }
    }



}
