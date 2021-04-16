package org.jenkinsci.plugins.blackshark.utility.steps.excel;

import com.alibaba.excel.EasyExcel;
import hudson.FilePath;
import org.apache.commons.lang.StringUtils;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;

import javax.annotation.Nonnull;
import java.io.FileNotFoundException;
import java.util.List;

public class WriteExcelStepExecution extends SynchronousNonBlockingStepExecution<Void> {
    private static final long serialVersionUID = 1L;

    private final transient WriteExcelStep step;

    protected WriteExcelStepExecution(@Nonnull WriteExcelStep step, @Nonnull StepContext context) {
        super(context);
        this.step = step;
    }


    @Override
    protected Void run() throws Exception {
        FilePath ws = getContext().get(FilePath.class);
        assert ws != null;

        List<?> list = step.getList();
        if (list == null) {
            throw new IllegalArgumentException(Messages.WriteExcelStepExecution_missingRecords(step.getDescriptor().getFunctionName()));
        }

        String file = step.getFile();
        if (StringUtils.isBlank(file)) {
            throw new IllegalArgumentException(Messages.WriteExcelStepExecution_missingFile(step.getDescriptor().getFunctionName()));
        }

        FilePath path = ws.child(file);
        if (path.isDirectory()) {
            throw new FileNotFoundException(Messages.ExcelStepExecution_fileIsDirectory(path.getRemote()));
        }

        EasyExcel.write(path.write()).sheet().doWrite(list);

        return null;
    }
}
