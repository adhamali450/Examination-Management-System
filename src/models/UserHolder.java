package models;

public final class UserHolder {

    private EndUser user;
    private final static UserHolder INSTANCE = new UserHolder();

    private UserHolder() {}

    public static UserHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(EndUser u) {
        this.user = u;
    }

    public EndUser getUser() {
        return this.user;
    }
}