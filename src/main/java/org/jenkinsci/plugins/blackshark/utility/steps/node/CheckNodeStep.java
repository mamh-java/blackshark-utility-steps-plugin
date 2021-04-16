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

public class CheckNodeStep extends Step {
    private String node;

    @DataBoundConstructor
    public CheckNodeStep() {
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new CheckNodeStepExecution(this, context);
    }

    public String getNode() {
        return node;
    }

    @DataBoundSetter
    public void setNode(String node) {
        this.node = node;
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
            return "checkNode";
        }

        @Override
        @Nonnull
        public String getDisplayName() {
            return Messages.CheckNodeStep_DescriptorImpl_displayName();
        }
    }

}
