package org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.impl;

import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.impl.BaseStandardCredentials;
import hudson.Extension;
import hudson.Util;
import hudson.util.Secret;
import org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.OpenCommerceAPICredentials;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class OpenCommerceAPICredentialsImpl extends BaseStandardCredentials implements OpenCommerceAPICredentials {

    private static final long serialVersionUID = 1L;

    private final String clientId;
    private final Secret clientPassword;

    @DataBoundConstructor
    public OpenCommerceAPICredentialsImpl(
            @CheckForNull CredentialsScope scope,
            @CheckForNull String id,
            @CheckForNull String description,
            @CheckForNull String clientId,
            @CheckForNull String clientPassword) {

        super(scope, id, description);

        this.clientId = Util.fixNull(clientId);
        this.clientPassword = Secret.fromString(clientPassword);
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Secret getClientPassword() {
        return clientPassword;
    }

    @Extension
    public static class DescriptorImpl extends BaseStandardCredentialsDescriptor {
        @Nonnull
        @Override
        public String getDisplayName() {
            return "OSF Builder Suite :: Open Commerce API Credentials";
        }
    }
}
