package models;

abstract public class EndUser {
    public static EndUser loggingUser;
    
    protected String username, password, name, phoneNumber, emailAddress;

    public abstract String getUsername();

    public abstract void setUsername(String username);

    public abstract void setPassword(String password);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getPhoneNumber();

    public abstract void setPhoneNumber(String phoneNumber);

    public abstract String getEmailAddress();

    public abstract void setEmailAddress(String emailAddress);
}
