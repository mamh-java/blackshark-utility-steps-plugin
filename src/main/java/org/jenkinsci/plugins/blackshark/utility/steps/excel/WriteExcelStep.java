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
import java.util.List;
import java.util.Set;


public class WriteExcelStep extends Step {

    private String file;
    private List<?> list;

    @DataBoundConstructor
    public WriteExcelStep(String file, List list) {
        this.file = file;
        this.list = list;
    }

    public String getFile() {
        return this.file;
    }

    @DataBoundSetter
    public void setFile(String file) {
        this.file = file;
    }

    public List<?> getList() {
        return list;
    }

    @DataBoundSetter
    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new WriteExcelStepExecution(this, context);
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
            return "writeExcel";
        }

        @Override
        @Nonnull
        public String getDisplayName() {
            return Messages.WriteExcelStep_DescriptorImpl_displayName();
        }
    }
}
