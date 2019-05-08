package org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.impl;

import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.impl.BaseStandardCredentials;
import hudson.Extension;
import hudson.Util;
import hudson.util.Secret;
import org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.GitHubCheckRunsCredentials;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class GitHubCheckRunsCredentialsImpl extends BaseStandardCredentials implements GitHubCheckRunsCredentials {

    private static final long serialVersionUID = 1L;

    private final String appId;
    private final String installationId;
    private final Secret privateKey;

    @DataBoundConstructor
    public GitHubCheckRunsCredentialsImpl(
            @CheckForNull CredentialsScope scope,
            @CheckForNull String id,
            @CheckForNull String description,
            @CheckForNull String appId,
            @CheckForNull String installationId,
            @CheckForNull String privateKey) {

        super(scope, id, description);

        this.appId = Util.fixNull(appId);
        this.installationId = Util.fixNull(installationId);
        this.privateKey = Secret.fromString(privateKey);
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public String getInstallationId() {
        return installationId;
    }

    @Override
    public Secret getPrivateKey() {
        return privateKey;
    }

    @Extension
    public static class DescriptorImpl extends BaseStandardCredentialsDescriptor {
        @Nonnull
        @Override
        public String getDisplayName() {
            return "OSF Builder Suite :: GitHub Check Runs Credentials";
        }
    }
}
