package org.jenkinsci.plugins.blackshark.utility.steps.node;

import hudson.model.Computer;
import jenkins.model.Jenkins;
import org.apache.commons.lang.StringUtils;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class FindNodeStepExecution extends SynchronousNonBlockingStepExecution<List<String>> {
    private static final long serialVersionUID = 1L;

    private transient final FindNodeStep step;

    public FindNodeStepExecution(FindNodeStep step, @Nonnull StepContext context) {
        super(context);
        this.step = step;
    }

    @Override
    protected List<String> run() throws Exception {
        List<String> nodeList = new ArrayList<>();
        Computer[] computers = Jenkins.get().getComputers();
        for (Computer computer : computers) {
            if (StringUtils.isNotBlank(step.getReg())) {
                if (computer.getName().contains(step.getReg())) {
                    nodeList.add(computer.getName());
                }
            } else {
                nodeList.add(computer.getName());
            }
        }

        return nodeList;
    }
}
