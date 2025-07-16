import java.util.*;

public class mpinValidation {

    public static void main(String[] args) {
        test("1234", "01021998", "15031998", "18062021", "WEAK", List.of("COMMONLY_USED"));
        test("0201", "02011998", "15031998", "18062021", "WEAK", List.of("DEMOGRAPHIC_DOB_SELF"));
        test("0315", "02011998", "15031998", "18062021", "WEAK", List.of("DEMOGRAPHIC_DOB_SPOUSE"));
        test("0621", "02011998", "15031998", "21062021", "WEAK", List.of("DEMOGRAPHIC_ANNIVERSARY"));
        test("1122", "01021998", "15031998", "18062021", "WEAK", List.of("COMMONLY_USED"));
        test("0201", "02011998", "02011998", "02011998", "WEAK", List.of("DEMOGRAPHIC_DOB_SELF", "DEMOGRAPHIC_DOB_SPOUSE", "DEMOGRAPHIC_ANNIVERSARY"));
        test("4567", "01011990", "15031991", "18062010", "STRONG", List.of());
        test("1998", "02011998", null, null, "WEAK", List.of("DEMOGRAPHIC_DOB_SELF"));
        test("1999", "02011998", null, null, "STRONG", List.of());
        test("1313", null, null, null, "WEAK", List.of("COMMONLY_USED"));
        test("2012", "20122012", null, null, "WEAK", List.of("DEMOGRAPHIC_DOB_SELF"));
        test("1503", null, "15031998", null, "WEAK", List.of("DEMOGRAPHIC_DOB_SPOUSE"));
        test("4444", null, null, null, "WEAK", List.of("COMMONLY_USED"));
        test("8888", null, null, null, "STRONG", List.of());
        test("0000", null, null, null, "WEAK", List.of("COMMONLY_USED"));
        test("2001", "20011990", null, null, "WEAK", List.of("COMMONLY_USED", "DEMOGRAPHIC_DOB_SELF"));
        test("0707", "07071995", "08081996", "09092000", "WEAK", List.of("DEMOGRAPHIC_DOB_SELF"));
        test("0808", "07071995", "08081996", "09092000", "WEAK", List.of("DEMOGRAPHIC_DOB_SPOUSE"));
        test("0909", "07071995", "08081996", "09092000", "WEAK", List.of("DEMOGRAPHIC_ANNIVERSARY"));
        test("6821", "02011998", "15031998", "18062021", "STRONG", List.of());
    }

    private static void test(String mpin, String dob, String spouse, String anniv, String expectedStrength, List<String> expectedReasons) {
        Map<String, Object> result = mpinTask.validateMPIN(mpin, dob, spouse, anniv);
        String strength = (String) result.get("strength");
        List<String> reasons = (List<String>) result.get("reasons");

        boolean strengthMatch = strength.equals(expectedStrength);
        boolean reasonsMatch = new HashSet<>(reasons).equals(new HashSet<>(expectedReasons));

        System.out.printf("MPIN: %s => Strength: %s, Reasons: %s [%s]%n", mpin, strength, reasons, (strengthMatch && reasonsMatch ? "PASS" : "FAIL"));
    }
}

