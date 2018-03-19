package org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials;

import com.cloudbees.plugins.credentials.CredentialsNameProvider;
import com.cloudbees.plugins.credentials.NameWith;
import com.cloudbees.plugins.credentials.common.StandardCredentials;
import hudson.util.Secret;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

@NameWith(BusinessManagerAuthCredentials.NameProvider.class)
public interface BusinessManagerAuthCredentials extends StandardCredentials {
    @SuppressWarnings("unused")
    String getUsername();

    @SuppressWarnings("unused")
    Secret getPassword();

    class NameProvider extends CredentialsNameProvider<BusinessManagerAuthCredentials> {
        @Nonnull
        @Override
        public String getName(@Nonnull BusinessManagerAuthCredentials credentials) {
            String description = StringUtils.trim(credentials.getDescription());
            if (StringUtils.isNotEmpty(description)) {
                return description;
            }

            return credentials.getId();
        }
    }
}
