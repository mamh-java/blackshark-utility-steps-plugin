package org.jenkinsci.plugins.blackshark.utility.steps.node;

import hudson.model.Computer;
import jenkins.model.Jenkins;
import org.apache.commons.lang.StringUtils;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class CheckNodeStepExecution extends SynchronousNonBlockingStepExecution<Boolean> {
    private static final long serialVersionUID = 1L;

    private transient final CheckNodeStep step;

    public CheckNodeStepExecution(CheckNodeStep step, @Nonnull StepContext context) {
        super(context);
        this.step = step;
    }

    @Override
    protected Boolean run() throws Exception {
        if (StringUtils.isNotBlank(step.getNode())) {
            if (step.getNode().equals("master")) {
                return true;
            }
            Computer computer = Jenkins.get().getComputer(step.getNode());
            if (computer != null) {
                return computer.isOnline();
            }
        }
        return false;
    }
}
