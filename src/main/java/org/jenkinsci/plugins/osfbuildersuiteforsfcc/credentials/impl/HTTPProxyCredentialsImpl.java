package org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.impl;

import com.cloudbees.plugins.credentials.CredentialsScope;
import com.cloudbees.plugins.credentials.impl.BaseStandardCredentials;
import hudson.Extension;
import hudson.Util;
import hudson.util.Secret;
import org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials.HTTPProxyCredentials;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class HTTPProxyCredentialsImpl extends BaseStandardCredentials implements HTTPProxyCredentials {

    private static final long serialVersionUID = 1L;

    private final String host;
    private final String port;
    private final String username;
    private final Secret password;

    @DataBoundConstructor
    public HTTPProxyCredentialsImpl(
            @CheckForNull CredentialsScope scope,
            @CheckForNull String id,
            @CheckForNull String description,
            @CheckForNull String host,
            @CheckForNull String port,
            @CheckForNull String username,
            @CheckForNull String password) {

        super(scope, id, description);

        this.host = Util.fixNull(host);
        this.port = Util.fixNull(port);
        this.username = Util.fixNull(username);
        this.password = Secret.fromString(password);
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getPort() {
        return port;
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
            return "OSF Builder Suite :: HTTP Proxy Credentials";
        }
    }
}
