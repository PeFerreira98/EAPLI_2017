package eapli.ecafeteria.domain.mealbooking;

public enum BookingState {
	BOOKED, CANCELLED, DELIVERED, NONDELIVERED;
	
	public boolean canCancel(){
		return (this == BOOKED);
	}
	
	public boolean canDeliver(){
		return (this == BOOKED);
	}
	
	public boolean canNonDeliver(){
		return (this == BOOKED);
	}
}
