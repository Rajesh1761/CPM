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
			String input = sc.nextLine();// This will accept the input from command line interface
			System.out.println(showParkingData(performParking(input.split(","))));
		} catch (Exception ex) {
			System.err.println("Exception occured while performing parking allocation " + ex);
		} finally {
			sc.close();
		}
	}

	static String showParkingData(HashMap<Integer, String> map) {// This method will print out as expected

		Iterator<Entry<Integer, String>> itr = map.entrySet().stream().sorted(Entry.<Integer, String>comparingByKey())
				.iterator();// This will sort the map in ascending order starting from 5000
		StringBuilder outPutCommaSeparated = new StringBuilder();
		while (itr.hasNext()) {
			Entry thisEntry = (Entry) itr.next();
			outPutCommaSeparated.append(thisEntry.getValue()).append(",");// Here we are iterating each value of map and
																			// appending comma (",") after each values
		}
		return outPutCommaSeparated.substring(0, outPutCommaSeparated.length() - 1);// This will remove comma (",") from
																					// last character

	}

	static HashMap<Integer, String> performParking(String[] inputSplit) {
		int ticketNumber = 5000;// Ticket Number should start with 5000
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		List<String> licensePlateNumer = new ArrayList<String>(10);
		boolean flagCheck = false;
		for (String splittedString : inputSplit) {
			if (ticketNumber < 5010) { // This car park has a total of 10 available spaces

				if (splittedString.startsWith("p") || splittedString.startsWith("P")) {// This will parked the vehicle

					map.put(ticketNumber, splittedString.substring(1, splittedString.length()));
					licensePlateNumer.add(splittedString.substring(1, splittedString.length()));
					ticketNumber++;
				} else if (splittedString.startsWith("u") || splittedString.startsWith("U")) {// This will unParked the
																								// vehicle

					int unParkedTicketNumber = Integer.parseInt(splittedString.substring(1, splittedString.length()));
					licensePlateNumer.remove(map.get(unParkedTicketNumber));
					map.remove(unParkedTicketNumber);
					ticketNumber--;
					if (flagCheck) {
						map = compactParking(licensePlateNumer);
					}
				} else if (splittedString.startsWith("c") || splittedString.startsWith("C")) {// This will fragment,
																								// compact the car park
					flagCheck = true;
					map = compactParking(licensePlateNumer);
				} else {
					System.out.println("invalid initial string for '" + splittedString
							+ "' please check and re-enter this values with start with 'p', 'u', 'c'");
				}
			}
		}
		if (flagCheck) {
			if (map.size() < 10) {
				for (int i = map.size(); i < 10; i++) {
					map.put(5000 + i, "");
				}
			}
		}
		return map;
	}

	private static HashMap<Integer, String> compactParking(List<String> licensePlateNumer) {/*This method will compact the parking space*/
		int ticketNumber = 5000;
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 0; i < licensePlateNumer.size(); i++) {
			map.put(ticketNumber, licensePlateNumer.get(i));
			ticketNumber++;
		}
		return map;
	}
}
