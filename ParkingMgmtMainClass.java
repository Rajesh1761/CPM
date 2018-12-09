import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class ParkingMgmtMainClass {

	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			String input = sc.nextLine();//This will accept the input from command line interface
			showParkingData(performParking(input.split(",")));
		} catch (Exception ex) {
			System.err
					.println("Exception occured while performing parking allocation "
							+ ex);
		} finally {
			sc.close();
		}
	}

	@SuppressWarnings("rawtypes")
	private static void showParkingData(HashMap<Integer, String> map) {// This method will print out as expected
		
		Iterator<Entry<Integer, String>> itr = map.entrySet().stream()
				.sorted(Entry.<Integer, String> comparingByKey()).iterator();//This will sort the map in ascending order starting from 5000
		StringBuilder outPutCommaSeparated = new StringBuilder();
		while (itr.hasNext()) {
			Entry thisEntry = (Entry) itr.next();
			outPutCommaSeparated.append(thisEntry.getValue()).append(",");//Here we are iterating each value of map and appending comma (",") after each values
		}
		System.out.println(outPutCommaSeparated.substring(0,outPutCommaSeparated.length()-1));//This will remove comma (",") from last character

	}

	private static HashMap<Integer, String> performParking(String[] inputSplit) {
		int ticketNumber = 5000;// Ticket Number should start with 5000
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		List<String> licensePlateNumer = new ArrayList<String>(10);
		for (String splittedString : inputSplit) {
			if (ticketNumber < 5010) { // This car park has a total of 10 available spaces

				if (splittedString.startsWith("p")
						|| splittedString.startsWith("P")) {// This will parked the vehicle

					map.put(ticketNumber, splittedString.substring(1,
							splittedString.length()));
					licensePlateNumer.add(splittedString.substring(1,
							splittedString.length()));
					ticketNumber++;
				} else if (splittedString.startsWith("u")
						|| splittedString.startsWith("U")) {// This will unParked the vehicle

					int unParkedTicketNumber = Integer.parseInt(splittedString
							.substring(1, splittedString.length()));
					licensePlateNumer.remove(map.get(unParkedTicketNumber));
					map.remove(unParkedTicketNumber);
					ticketNumber--;
				} else if (splittedString.startsWith("c")
						|| splittedString.startsWith("C")) {// This will fragment, compact the car park

					ticketNumber = 5000;
					map = new HashMap<Integer, String>();
					for (int i = 0; i < 10; i++) {
						try {
							map.put(ticketNumber, licensePlateNumer.get(i));
						} catch (IndexOutOfBoundsException ex) {
							map.put(ticketNumber, "");// Adding blank values in map because in output they are expecting blank values when 
													  //compacting the parking space
						}

						ticketNumber++;
					}
				} else {
					System.out.println("invalid initial string for '"
							+ splittedString
							+ "' please check and re-enter this values with start with 'p', 'u', 'c'");
				}
			} else {
				System.out
						.println("Parking slot is not available for license plate '"
								+ splittedString
								+ "', Please unParked some vehicle.");
			}

		}
		if(map.size() < 10){
			
		}
		return map;
	}
}
