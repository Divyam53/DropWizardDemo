package test.divyam.ePayLaterTest.apis.login;

import java.security.Principal;
import java.util.Objects;

public class MyUser implements Principal {
	
	private final String name;
	
	public MyUser(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	 @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MyUser myUser = (MyUser) o;
        return Objects.equals(name, myUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
