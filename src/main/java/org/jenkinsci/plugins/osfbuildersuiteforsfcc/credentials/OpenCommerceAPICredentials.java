package org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials;

import com.cloudbees.plugins.credentials.CredentialsNameProvider;
import com.cloudbees.plugins.credentials.NameWith;
import com.cloudbees.plugins.credentials.common.StandardCredentials;
import hudson.util.Secret;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

@NameWith(OpenCommerceAPICredentials.NameProvider.class)
public interface OpenCommerceAPICredentials extends StandardCredentials {
    @SuppressWarnings("unused")
    String getClientId();

    @SuppressWarnings("unused")
    Secret getClientPassword();

    class NameProvider extends CredentialsNameProvider<OpenCommerceAPICredentials> {
        @Nonnull
        @Override
        public String getName(@Nonnull OpenCommerceAPICredentials credentials) {
            String description = StringUtils.trim(credentials.getDescription());
            if (StringUtils.isNotEmpty(description)) {
                return description;
            }

            return credentials.getId();
        }
    }
}
