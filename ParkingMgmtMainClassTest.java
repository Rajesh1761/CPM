import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParkingMgmtMainClassTest {

	@Test
	public void TestCase1() {
		String input = "c,pABC,pXYZ,pEFG,U5000";
		assertEquals(ParkingMgmtMainClass.showParkingData(ParkingMgmtMainClass.performParking(input.split(","))),
				"XYZ,EFG,,,,,,,,");
	}

	@Test
	public void TestCase2() {
		String input = "pABC,pXYZ,U5000,c,pGHI";
		assertEquals(ParkingMgmtMainClass.showParkingData(ParkingMgmtMainClass.performParking(input.split(","))),
				"XYZ,GHI,,,,,,,,");
	}

	@Test
	public void TestCase3() {
		String input = "pABC,pXYZ,c,U5000,pGHI";
		assertEquals(ParkingMgmtMainClass.showParkingData(ParkingMgmtMainClass.performParking(input.split(","))),
				"XYZ,GHI,,,,,,,,");
	}

	@Test
	public void TestCase4() {
		String input = "pABC,pXYZ,pGHI";
		assertEquals(ParkingMgmtMainClass.showParkingData(ParkingMgmtMainClass.performParking(input.split(","))),
				"ABC,XYZ,GHI");
	}

	@Test
	public void TestCase5() {
		String input = "c,pABC,pXYZ,U5000,pGHI";
		assertEquals(ParkingMgmtMainClass.showParkingData(ParkingMgmtMainClass.performParking(input.split(","))),
				"XYZ,GHI,,,,,,,,");
	}

	@Test
	public void TestCase6() {
		String input = "pABC,pXYZ,pEFG,U5000,c";
		assertEquals(ParkingMgmtMainClass.showParkingData(ParkingMgmtMainClass.performParking(input.split(","))),
				"XYZ,EFG,,,,,,,,");
	}
}
