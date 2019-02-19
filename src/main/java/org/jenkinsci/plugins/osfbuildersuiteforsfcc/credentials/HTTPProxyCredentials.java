package org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials;

import com.cloudbees.plugins.credentials.CredentialsNameProvider;
import com.cloudbees.plugins.credentials.NameWith;
import com.cloudbees.plugins.credentials.common.StandardCredentials;
import hudson.util.Secret;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

@NameWith(HTTPProxyCredentials.NameProvider.class)
public interface HTTPProxyCredentials extends StandardCredentials {
    @SuppressWarnings("unused")
    String getHost();

    @SuppressWarnings("unused")
    String getPort();

    @SuppressWarnings("unused")
    String getUsername();

    @SuppressWarnings("unused")
    Secret getPassword();

    class NameProvider extends CredentialsNameProvider<HTTPProxyCredentials> {
        @Nonnull
        @Override
        public String getName(@Nonnull HTTPProxyCredentials credentials) {
            String description = StringUtils.trim(credentials.getDescription());
            if (StringUtils.isNotEmpty(description)) {
                return description;
            }

            return credentials.getId();
        }
    }
}
