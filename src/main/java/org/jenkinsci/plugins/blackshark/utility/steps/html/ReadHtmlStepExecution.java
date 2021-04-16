package org.jenkinsci.plugins.blackshark.utility.steps.html;

import hudson.FilePath;
import org.jenkinsci.plugins.workflow.steps.MissingContextVariableException;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.lang.StringUtils.isNotBlank;

public class ReadHtmlStepExecution  extends SynchronousNonBlockingStepExecution<String> {
    private static final long serialVersionUID = 1L;

    private final ReadHtmlStep step;

    public ReadHtmlStepExecution(ReadHtmlStep step, @Nonnull StepContext context) {
        super(context);
        this.step = step;
    }

    @Override
    protected String run() throws Exception {
        FilePath ws = getContext().get(FilePath.class);
        if (ws == null && isNotBlank(step.getFile())) {
            throw new MissingContextVariableException(FilePath.class);
        }

        FilePath f = ws.child(step.getFile());
        if (f.exists() && !f.isDirectory()) {
            try(InputStream is = f.read()){
                String html = IOUtils.toString(is, StandardCharsets.UTF_8);
                Document document = Jsoup.parse(html);
                Elements body = document.getElementsByTag("body");
                String bodyStr = body.html();
                return bodyStr;
            }
        }
        return "";
    }
}
