package org.jenkinsci.plugins.blackshark.utility.steps.toml;

import com.moandjiezana.toml.Toml;
import hudson.FilePath;
import org.apache.commons.io.IOUtils;
import org.jenkinsci.plugins.workflow.steps.MissingContextVariableException;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class ReadTomlStepExecution extends SynchronousNonBlockingStepExecution<Object> {
    private static final long serialVersionUID = 1L;

    private transient final ReadTomlStep step;


    public ReadTomlStepExecution(ReadTomlStep step, StepContext context) {
        super(context);
        this.step = step;

    }

    @Override
    protected Object run() throws Exception {
        FilePath ws = getContext().get(FilePath.class);
        if (ws == null && isNotBlank(step.getFile())) {
            throw new MissingContextVariableException(FilePath.class);
        }

        Toml toml = new Toml();
        if (!isBlank(step.getFile())) {
            FilePath f = ws.child(step.getFile());
            if (f.exists() && !f.isDirectory()) {
                try (InputStream is = f.read()) {
                    toml.read(IOUtils.toString(is, StandardCharsets.UTF_8));
                }
            } else if (f.isDirectory()) {
                throw new IllegalArgumentException(Messages.TomlStepExecution_fileIsDirectory(f.getRemote()));
            } else if (!f.exists()) {
                throw new FileNotFoundException(Messages.TomlStepExecution_fileNotFound(f.getRemote()));
            }
        }
        Map<String, Object> map = toml.toMap();

        return map;
    }
}
