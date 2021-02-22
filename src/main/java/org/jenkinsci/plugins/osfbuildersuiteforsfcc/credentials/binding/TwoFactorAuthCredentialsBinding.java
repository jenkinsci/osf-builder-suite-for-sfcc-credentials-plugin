package org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.binding;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.Run;
import hudson.model.TaskListener;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.credentialsbinding.BindingDescriptor;
import org.jenkinsci.plugins.credentialsbinding.MultiBinding;
import org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.TwoFactorAuthCredentials;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.*;

public class TwoFactorAuthCredentialsBinding extends MultiBinding<TwoFactorAuthCredentials> {

    private final String serverCertificateVariable;
    private final String clientCertificateVariable;
    private final String clientPrivateKeyVariable;

    @DataBoundConstructor
    public TwoFactorAuthCredentialsBinding(
            String serverCertificateVariable,
            String clientCertificateVariable,
            String clientPrivateKeyVariable,
            String credentialsId) {

        super(credentialsId);
        this.serverCertificateVariable = serverCertificateVariable;
        this.clientCertificateVariable = clientCertificateVariable;
        this.clientPrivateKeyVariable = clientPrivateKeyVariable;
    }

    public String getServerCertificateVariable() {
        return serverCertificateVariable;
    }

    public String getClientCertificateVariable() {
        return clientCertificateVariable;
    }

    public String getClientPrivateKeyVariable() {
        return clientPrivateKeyVariable;
    }

    @Override
    protected Class<TwoFactorAuthCredentials> type() {
        return TwoFactorAuthCredentials.class;
    }

    @Override
    public MultiEnvironment bind(
            @Nonnull Run<?, ?> run,
            @Nullable FilePath filePath,
            @Nullable Launcher launcher,
            @Nonnull TaskListener taskListener) throws IOException {

        TwoFactorAuthCredentials c = getCredentials(run);
        Map<String, String> m = new HashMap<>();
        m.put(serverCertificateVariable, c.getServerCertificate());
        m.put(clientCertificateVariable, c.getClientCertificate());
        m.put(clientPrivateKeyVariable, c.getClientPrivateKey());
        return new MultiEnvironment(m);
    }

    @Override
    public Set<String> variables() {
        return new HashSet<>(Arrays.asList(
                serverCertificateVariable,
                clientCertificateVariable,
                clientPrivateKeyVariable
        ));
    }

    @Symbol("OSFBuilderSuiteTwoFactorAuthCredentials")
    @Extension
    public static class DescriptorImpl extends BindingDescriptor<TwoFactorAuthCredentials> {

        @Override
        protected Class<TwoFactorAuthCredentials> type() {
            return TwoFactorAuthCredentials.class;
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "OSF Builder Suite :: Two Factor Auth Credentials";
        }
    }
}
