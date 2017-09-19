package org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.impl;

import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.impl.BaseStandardCredentials;
import hudson.Extension;
import hudson.Util;
import org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.TwoFactorAuthCredentials;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class TwoFactorAuthCredentialsImpl extends BaseStandardCredentials implements TwoFactorAuthCredentials {

    private static final long serialVersionUID = 1L;

    private final String serverCertificate;
    private final String clientCertificate;
    private final String clientPrivateKey;

    @DataBoundConstructor
    public TwoFactorAuthCredentialsImpl(@CheckForNull CredentialsScope scope,
                                        @CheckForNull String id,
                                        @CheckForNull String description,
                                        @CheckForNull String serverCertificate,
                                        @CheckForNull String clientCertificate,
                                        @CheckForNull String clientPrivateKey) {

        super(scope, id, description);

        this.serverCertificate = Util.fixNull(serverCertificate);
        this.clientCertificate = Util.fixNull(clientCertificate);
        this.clientPrivateKey = Util.fixNull(clientPrivateKey);
    }

    @Override
    public String getServerCertificate() {
        return serverCertificate;
    }

    @Override
    public String getClientCertificate() {
        return clientCertificate;
    }

    @Override
    public String getClientPrivateKey() {
        return clientPrivateKey;
    }

    @Extension
    public static class DescriptorImpl extends BaseStandardCredentialsDescriptor {
        @Nonnull
        @Override
        public String getDisplayName() {
            return "OSF Builder Suite :: Two Factor Auth";
        }
    }
}
