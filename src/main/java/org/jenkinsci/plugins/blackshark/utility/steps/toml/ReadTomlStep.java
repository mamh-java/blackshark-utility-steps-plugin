package org.jenkinsci.plugins.blackshark.utility.steps.toml;

import com.google.common.collect.ImmutableSet;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.FilePath;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.workflow.steps.Step;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepDescriptor;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.Set;

public class ReadTomlStep extends Step {
    private String file;

    @DataBoundConstructor
    public ReadTomlStep(String file) {
        this.file = file;
    }


    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new ReadTomlStepExecution(this, context);
    }

    public String getFile() {
        return file;
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
            return "readTOML";
        }

        @Override
        @NonNull
        public String getDisplayName() {
            return Messages.ReadTomlStep_DescriptorImpl_displayName();
        }
    }
}
