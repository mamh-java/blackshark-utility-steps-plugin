package org.jenkinsci.plugins.blackshark.utility.steps.node;

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

public class FindNodeStep extends Step {
    private String reg;

    @DataBoundConstructor
    public FindNodeStep() {
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new FindNodeStepExecution(this, context);
    }

    public String getReg() {
        return reg;
    }

    @DataBoundSetter
    public void setReg(String reg) {
        this.reg = reg;
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
            return "findNode";
        }

        @Override
        @Nonnull
        public String getDisplayName() {
            return Messages.FindNodeStep_DescriptorImpl_displayName();
        }
    }

}
