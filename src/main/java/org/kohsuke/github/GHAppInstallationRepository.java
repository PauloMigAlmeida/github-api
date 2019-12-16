package org.kohsuke.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.List;
import java.util.Objects;

/**
 * A Github Installation Repository.
 *
 * @author Paulo Miguel Almeida
 * @see GitHub#listInstallationRepositories()
 * @see <a href="https://developer.github.com/v3/apps/installations/#list-repositories">List repositories</a>
 */
public class GHAppInstallationRepository {
    private GitHub root;
    @SuppressFBWarnings(value = "UWF_UNWRITTEN_FIELD", justification = "Field comes from JSON deserialization")
    @JsonProperty("total_count")
    private int totalCount;
    @SuppressFBWarnings(value = "UWF_UNWRITTEN_FIELD", justification = "Field comes from JSON deserialization")
    private List<GHRepository> repositories;

    @SuppressFBWarnings(value = "NP_UNWRITTEN_FIELD", justification = "Constructed by JSON deserialization")
    GHAppInstallationRepository wrapUp(GitHub root) {
        this.root = root;
        if (Objects.nonNull(this.repositories)) // sanity check
            repositories.forEach(repo -> repo.wrap((this.root)));
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<GHRepository> getRepositories() {
        return repositories;
    }

}
