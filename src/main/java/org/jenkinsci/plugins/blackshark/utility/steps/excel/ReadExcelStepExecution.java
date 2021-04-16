package org.jenkinsci.plugins.blackshark.utility.steps.excel;

import com.alibaba.excel.EasyExcel;
import hudson.FilePath;
import org.jenkinsci.plugins.workflow.steps.MissingContextVariableException;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;


public class ReadExcelStepExecution extends SynchronousNonBlockingStepExecution<List> {
    private static final long serialVersionUID = 1L;

    private final transient ReadExcelStep step;

    protected ReadExcelStepExecution(@Nonnull ReadExcelStep step, @Nonnull StepContext context) {
        super(context);
        this.step = step;
    }

    @Override
    protected List run() throws Exception {
        FilePath ws = getContext().get(FilePath.class);
        if (ws == null && isNotBlank(step.getFile())) {
            throw new MissingContextVariableException(FilePath.class);
        }
        List list = new ArrayList();
        if (isNotBlank(step.getFile())) {
            FilePath path = ws.child(step.getFile());
            if (path.exists() && !path.isDirectory()) {
                list = EasyExcel.read(path.read()).sheet().doReadSync();
            }
        }
        return list;

    }
}
