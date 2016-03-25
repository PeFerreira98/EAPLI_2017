package eapli.ecafeteria.domain.users;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import eapli.framework.authz.Authorisable;
import eapli.framework.domain.AggregateRoot;
import eapli.framework.dto.DTOable;
import eapli.framework.dto.GenericDTO;
import eapli.util.DateTime;

/**
 * a system user
 * 
 * @author pgsou_000
 *
 */
@Entity
public class User implements AggregateRoot<Username>, Authorisable<ActionRight>, DTOable<User>, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Username		  username;
	private Password		  password;
	private Name			  name;
	private EmailAddress	  email;
	private RoleList		  roles;
	@Temporal(TemporalType.DATE)
	private Calendar		  createdOn;

	public User(String username, String password, String firstName, String lastName, String email,
	        List<RoleType> roles) {
		if (roles == null) {
			throw new IllegalArgumentException("roles cannot be null");
		}
		createdOn = DateTime.now();
		this.username = new Username(username);
		this.password = new Password(password);
		name = new Name(firstName, lastName);
		this.email = new EmailAddress(email);
		this.roles = new RoleList();
		for (final RoleType rt : roles) {
			this.roles.add(new Role(rt, createdOn));
		}
	}

	protected User() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}

		final User user = (User) o;

		if (!username.equals(user.username)) {
			return false;
		}

		// FIXME DDD entities are only compared thru their ID field. in this
		// case only username should be compared
		if (!password.equals(user.password)) {
			return false;
		}
		if (!name.equals(user.name)) {
			return false;
		}
		if (!email.equals(user.email)) {
			return false;
		}
		return roles.equals(user.roles);

	}

	@Override
	public int hashCode() {
		int result = username.hashCode();
		// FIXME hash should only use username field
		result = 31 * result + password.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + email.hashCode();
		result = 31 * result + roles.hashCode();
		return result;
	}

	@Override
	public Username id() {
		return username;
	}

	/**
	 * Add role to user
	 *
	 * @param role
	 */
	public void addRole(Role role) {
		roles.add(role);
	}

	@Override
	public GenericDTO toDTO() {
		final GenericDTO ret = new GenericDTO("user");
		ret.put("username", username.toString());
		ret.put("password", password.toString());
		ret.put("name", name.toString());
		ret.put("email", email.toString());

		return ret;
	}

	/**
	 * Add role to user
	 *
	 * @param role
	 */
	public void removeRole(Role role) {
		roles.remove(role);
	}

	@Override
	public boolean isAuthorizedTo(ActionRight action) {
		return action.canBePerformedBy(roles.roleTypes());
	}

	// TODO this method's name suggests a boolean return not a void
	// we are using exception handling for logic behavior...
	public void passwordMatches(Password password) throws InvalidPasswordException {
		if (!this.password.equals(password)) {
			throw new InvalidPasswordException("Password does note match", this);
		}
	}

	@Override
	public boolean is(Username id) {
		return id().equals(id);
	}
}
