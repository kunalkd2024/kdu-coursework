package org.example.q2;

import java.util.*;

public class TicketReservation {
    private static final int CONFIRMEDLIST_LIMIT = 10;
    private static final int WAITINGLIST_LIMIT = 3;

    private final List<Passenger> confirmedList = new ArrayList<>();
    private final Deque<Passenger> waitingList = new ArrayDeque<>();

    public List<Passenger> getConfirmedList(){
        return confirmedList;
    }

    public Deque<Passenger> getWaitingList() {
        return waitingList;
    }

    /**
     * Books a flight for a passenger with the given details and adds them to either the confirmed list or waiting list.
     * @return True if booking is successful, false if both confirmed and waiting lists are full
     */
    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass, String confirmationNumber) {
        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);
        if(confirmedList.size() < CONFIRMEDLIST_LIMIT){
            confirmedList.add(passenger);
            return true;
        }
        else if(waitingList.size() < WAITINGLIST_LIMIT){
            waitingList.add(passenger);
            return true;
        }
        return false;
    }
    /**
     * Cancels a flight booking based on the confirmation number.
     * Removes the passenger from the confirmed list, and if the waiting list is not empty, adds the next passenger to the confirmed list.
     * @return True if cancellation is successful, false if the confirmation number is not found in both lists
     */
    public boolean cancel(String confirmationNumber) {
        Iterator<Passenger> confirmedIterator = confirmedList.iterator();
        boolean checkRemove = removePassenger(confirmedIterator, confirmationNumber);

        if(checkRemove){
            if(!waitingList.isEmpty()){
                Passenger nextPassenger = waitingList.poll();
                confirmedList.add(nextPassenger);
            }
            return true;
        }
        else{
            Iterator<Passenger> waitingIterator = waitingList.iterator();
            return removePassenger(waitingIterator, confirmationNumber);
        }
    }

    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while(iterator.hasNext()){
            Passenger passenger = iterator.next();
            if(passenger.getConfirmationNumber().equals(confirmationNumber)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
