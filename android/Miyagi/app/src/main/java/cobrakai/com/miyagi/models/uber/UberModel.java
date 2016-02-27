package cobrakai.com.miyagi.models.uber;

/**
 * Created by m.stanford on 2/26/16.
 */
public class UberModel {
    private String login;
    private String blog;
    private int public_repos;

    public String getLogin() {
        return login;
    }

    public String getBlog() {
        return blog;
    }

    public int getPublicRepos() {
        return public_repos;
    }

    @Override
    public String toString() {
        return "UberModel{" +
                "login='" + login + '\'' +
                ", blog='" + blog + '\'' +
                ", public_repos=" + public_repos +
                '}';
    }
}
