package org.jenkinsci.plugins.blackshark.utility.steps.html;

import com.google.common.collect.ImmutableSet;
import hudson.Extension;
import hudson.FilePath;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.blackshark.utility.steps.html.Messages;
import org.jenkinsci.plugins.workflow.steps.Step;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepDescriptor;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import javax.annotation.Nonnull;
import java.util.Set;

public class ReadHtmlStep extends Step {
    private String file;
    private String tag;

    @DataBoundConstructor
    public ReadHtmlStep() {
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new ReadHtmlStepExecution(this, context);
    }

    public String getFile() {
        return this.file;
    }

    @DataBoundSetter
    public void setFile(String file) {
        this.file = file;
    }

    public String getTag() {
        return tag;
    }

    @DataBoundSetter
    public void setTag(String tag) {
        this.tag = tag;
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
            return "readHtml";
        }

        @Override
        @Nonnull
        public String getDisplayName() {
            return Messages.ReadHtmlStep_DescriptorImpl_displayName();
        }
    }
}
