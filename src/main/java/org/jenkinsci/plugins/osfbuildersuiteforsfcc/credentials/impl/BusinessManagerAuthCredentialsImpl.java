package org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.impl;

import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.impl.BaseStandardCredentials;
import hudson.Extension;
import hudson.Util;
import hudson.util.Secret;
import org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.BusinessManagerAuthCredentials;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class BusinessManagerAuthCredentialsImpl extends BaseStandardCredentials implements BusinessManagerAuthCredentials {

    private static final long serialVersionUID = 1L;

    private final String username;
    private final Secret password;

    @DataBoundConstructor
    public BusinessManagerAuthCredentialsImpl(
            @CheckForNull CredentialsScope scope,
            @CheckForNull String id,
            @CheckForNull String description,
            @CheckForNull String username,
            @CheckForNull String password) {

        super(scope, id, description);

        this.username = Util.fixNull(username);
        this.password = Secret.fromString(password);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Secret getPassword() {
        return password;
    }

    @Extension
    public static class DescriptorImpl extends BaseStandardCredentialsDescriptor {
        @Nonnull
        @Override
        public String getDisplayName() {
            return "OSF Builder Suite :: Business Manager Credentials";
        }
    }
}
