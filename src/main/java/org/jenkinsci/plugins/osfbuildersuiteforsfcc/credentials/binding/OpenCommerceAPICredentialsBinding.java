package org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.binding;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.Run;
import hudson.model.TaskListener;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.credentialsbinding.BindingDescriptor;
import org.jenkinsci.plugins.credentialsbinding.MultiBinding;
import org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.OpenCommerceAPICredentials;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.*;

public class OpenCommerceAPICredentialsBinding extends MultiBinding<OpenCommerceAPICredentials> {

    private final String clientIdVariable;
    private final String clientPasswordVariable;

    @DataBoundConstructor
    public OpenCommerceAPICredentialsBinding(
            String clientIdVariable,
            String clientPasswordVariable,
            String credentialsId) {

        super(credentialsId);
        this.clientIdVariable = clientIdVariable;
        this.clientPasswordVariable = clientPasswordVariable;
    }

    public String getClientIdVariable() {
        return clientIdVariable;
    }

    public String getClientPasswordVariable() {
        return clientPasswordVariable;
    }

    @Override
    protected Class<OpenCommerceAPICredentials> type() {
        return OpenCommerceAPICredentials.class;
    }

    @Override
    public MultiEnvironment bind(
            @Nonnull Run<?, ?> run,
            @Nullable FilePath filePath,
            @Nullable Launcher launcher,
            @Nonnull TaskListener taskListener) throws IOException {

        OpenCommerceAPICredentials c = getCredentials(run);
        Map<String, String> m = new HashMap<>();
        m.put(clientIdVariable, c.getClientId());
        m.put(clientPasswordVariable, c.getClientPassword().getPlainText());
        return new MultiEnvironment(m);
    }

    @Override
    public Set<String> variables() {
        return new HashSet<>(Arrays.asList(
                clientIdVariable,
                clientPasswordVariable
        ));
    }

    @Symbol("OSFBuilderSuiteOpenCommerceAPICredentials")
    @Extension
    public static class DescriptorImpl extends BindingDescriptor<OpenCommerceAPICredentials> {

        @Override
        protected Class<OpenCommerceAPICredentials> type() {
            return OpenCommerceAPICredentials.class;
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "OSF Builder Suite :: Open Commerce API Credentials";
        }
    }
}
