package org.jenkinsci.plugins.osfbuildersuiteforsfcc.credentials;

import com.cloudbees.plugins.credentials.CredentialsNameProvider;
import com.cloudbees.plugins.credentials.NameWith;
import com.cloudbees.plugins.credentials.common.StandardCredentials;
import hudson.util.Secret;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

@NameWith(GitHubCheckRunsCredentials.NameProvider.class)
public interface GitHubCheckRunsCredentials extends StandardCredentials {
    @SuppressWarnings("unused")
    String getAppId();

    @SuppressWarnings("unused")
    String getInstallationId();

    @SuppressWarnings("unused")
    Secret getPrivateKey();

    class NameProvider extends CredentialsNameProvider<GitHubCheckRunsCredentials> {
        @Nonnull
        @Override
        public String getName(@Nonnull GitHubCheckRunsCredentials credentials) {
            String description = StringUtils.trim(credentials.getDescription());
            if (StringUtils.isNotEmpty(description)) {
                return description;
            }

            return credentials.getId();
        }
    }
}
